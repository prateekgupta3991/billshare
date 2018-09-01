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

//    private void updateUsersBalanceInGroup(Long grpId, Bill bill) {
//
//        Group grp = groupDao.findById(grpId);
//        List<User> grpUsers = grp.getUsers();
//        for(User userItr : grpUsers) {
//            Contribution currentUserContriPaid = bill.getUserContributions().get(userItr);
//            Contribution currentUserContriOwe = bill.getUserOwed().get(userItr);
//
//            Double sharePaid = getUserPaidAmount(currentUserContriPaid, bill.getBillAmount());
//            Double shareOwe = getUserOweAmount(currentUserContriOwe, bill.getBillAmount());
//
//            Double netAmt = sharePaid - shareOwe;
//            Double grpUserAmt = userItr.getGroupWiseAmount().get(grp);
//            Double newGrpUserAmt = null;
//            if(grpUserAmt != null)
//                newGrpUserAmt = netAmt + grpUserAmt;
//            else
//                newGrpUserAmt = netAmt;
//            userItr.getGroupWiseAmount().put(grp, newGrpUserAmt);
//
//            Double userTotalAmt = userItr.getTotalAmount();
//            if(userTotalAmt != null)
//                userItr.setTotalAmount(userTotalAmt + newGrpUserAmt);
//            else
//                userItr.setTotalAmount(newGrpUserAmt);
//        }
//    }

    private Double getUserOweAmount(Contribution currentUserContriOwe, Double billAmt) {
        Double share = calculateShare(currentUserContriOwe, billAmt);
        return share;
    }

    private Double getUserPaidAmount(Contribution currentUserContriPaid, Double billAmt) {
        Double share = calculateShare(currentUserContriPaid, billAmt);
        return share;
    }

    private Double calculateShare(Contribution contri, Double billAmount) {

        Double share = null;
        if(contri.getShareAmount() != null) {
            share = contri.getShareAmount();
        } else if(contri.getSharePercentage() != null) {
            share = contri.getSharePercentage() * billAmount / 100.0;
        }
        return share;
    }
}
