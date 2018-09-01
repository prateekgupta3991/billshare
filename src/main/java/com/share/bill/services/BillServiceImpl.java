package com.share.bill.services;

import com.share.bill.dao.BillDao;
import com.share.bill.dao.BillUserGroupDao;
import com.share.bill.dao.GroupDao;
import com.share.bill.dao.UserDao;
import com.share.bill.dto.BillRequestDto;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.entities.*;
import com.share.bill.exceptions.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by prateekgupta on 09/09/17.
 */

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BillDao billDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private BillUserGroupDao billUserGroupDao;

    @Transactional
    @Override
    public void addBillToGroup(BillRequestDto billRequestDto) {

        Group grp = null;
        if (billRequestDto.getGrpId() != null) {
            grp = groupDao.findById(billRequestDto.getGrpId());
            if (grp == null) {
                throw new GroupNotFoundException("Group with id " + billRequestDto.getGrpId() + " does not exists");
            }
        }
        List<BillUserGroup> billUserGroupList = calculateShares(billRequestDto, grp);
        Bill bill = new Bill(billRequestDto.getBillName(), billRequestDto.getAmount(), grp);
        billDao.persist(bill);
        for (BillUserGroup billUserGroup : billUserGroupList) {
            billUserGroup.setBill(bill);
        }
        billUserGroupDao.persistAll(billUserGroupList);
    }

    @Transactional
    @Override
    public void addBill(BillRequestDto billRequestDto) {

        List<BillUserGroup> billUserGroupList = calculateShares(billRequestDto, null);
        Bill bill = new Bill(billRequestDto.getBillName(), billRequestDto.getAmount());
        billDao.persist(bill);
        for (BillUserGroup billUserGroup : billUserGroupList) {
            billUserGroup.setBill(bill);
        }
        billUserGroupDao.persistAll(billUserGroupList);
    }

    private List<BillUserGroup> calculateShares(BillRequestDto billRequestDto, Group grp) {

        List<BillUserGroup> billUserGroups;
        if (grp != null) {
            billUserGroups = splitGroupUserShares(billRequestDto, grp);
        } else {
            billUserGroups = splitUserShares(billRequestDto);
        }
        return billUserGroups;
    }

    private List<BillUserGroup> splitGroupUserShares(BillRequestDto billRequestDto, Group grp) {

        List<User> userList = getUsersInBill(billRequestDto);//prepareGroupUserList(grp); <Use this afterwards. Needs fixing>
        List<BillUserGroup> billUserGroups = calculateUserShares(billRequestDto, userList);
        return billUserGroups;
    }

    private List<User> prepareGroupUserList(Group grp) {

        List<User> idList = new ArrayList<>();
        for (UserGroup usrGrp : grp.getUserGroups()) {
            idList.add(usrGrp.getUser());
        }
        return idList;
    }

    private List<BillUserGroup> splitUserShares(BillRequestDto billRequestDto) {

        List<User> userList = getUsersInBill(billRequestDto);
        List<BillUserGroup> billUserGroups = calculateUserShares(billRequestDto, userList);
        return billUserGroups;
    }

    private List<User> getUsersInBill(BillRequestDto billRequestDto) {

        Set<Long> oweIds = billRequestDto.getUserContriOwe().keySet();
        Set<Long> paidIds = billRequestDto.getUserContriPaid().keySet();
        Set<Long> ids = new HashSet<>();
        ids.addAll(oweIds);
        ids.addAll(paidIds);
        List<Long> idList = new ArrayList<>();
        idList.addAll(ids);
        List<User> userList = userDao.findAllByIds(idList);
        return userList;
    }

    private List<BillUserGroup> calculateUserShares(BillRequestDto billRequestDto, List<User> users) {

        Double paidAmt = -1.0, owedAmt = -1.0, paidPer = 0.0, owedPer = 0.0;
        List<BillUserGroup> billUserGroups = new ArrayList<>();
        for (User user : users) {
            // check if data is supplied for correct user share calculations
            Map<Long, Contribution> userContriPaid = billRequestDto.getUserContriPaid();
            Map<Long, Contribution> userContriOwe = billRequestDto.getUserContriOwe();
            Contribution userPaidContribution = userContriPaid.get(user.getId());
            Contribution userOwedContribution = userContriOwe.get(user.getId());
            if((userPaidContribution != null && userPaidContribution.getShareAmount() == null && userPaidContribution.getSharePercentage() == null)
                    || (userOwedContribution != null && userOwedContribution.getShareAmount() == null && userOwedContribution.getSharePercentage() == null)) {
                System.out.println("No share data supplied");
                System.exit(0);
            } else {
                if (userPaidContribution != null) {
                    if (userPaidContribution.getShareAmount() != null) {
                        paidAmt = userPaidContribution.getShareAmount();
                    } else {
                        paidPer = userPaidContribution.getSharePercentage();
                    }
                }
                if (userOwedContribution != null) {
                    if (userOwedContribution.getShareAmount() != null) {
                        owedAmt = userOwedContribution.getShareAmount();
                    } else {
                        owedPer = userOwedContribution.getSharePercentage();
                    }
                }
            }
            Double userPaid = paidAmt != -1.0 ? paidAmt : paidPer * 0.01 * billRequestDto.getAmount();
            Double userOwe = owedAmt != -1.0 ? owedAmt : owedPer * 0.01 * billRequestDto.getAmount();
            BillUserGroup billUserGroup = new BillUserGroup(user, userPaid - userOwe);
            billUserGroups.add(billUserGroup);
            paidAmt = -1.0;
            owedAmt = -1.0;
        }
        return billUserGroups;
    }

    public BillRequestDto getBillDetails(Long billId) {

        Bill bill = billDao.findById(billId);
        Long grpId = bill.getGang() != null ? bill.getGang().getId() : null;
        BillRequestDto billRequestDto = new BillRequestDto(bill.getName(), bill.getBillAmount(), grpId);
        List<BillUserGroup> billUserGroups = billUserGroupDao.findBillUserGroupByBillId(billId);
        Map<Long, Contribution> owe = new HashMap<>();
        Map<Long, Contribution> paid = new HashMap<>();
        billRequestDto.setUserContriOwe(owe);
        billRequestDto.setUserContriPaid(paid);
        for (BillUserGroup billUserGroup : billUserGroups) {
            Contribution contribution = new Contribution(billUserGroup.getShare(), null);
            if (billUserGroup.getShare() > 0) {
                paid.put(billUserGroup.getUser().getId(), contribution);
            } else {
                owe.put(billUserGroup.getUser().getId(), contribution);
            }
        }
        return billRequestDto;
    }
}
