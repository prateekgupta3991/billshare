package com.share.bill.services;

import com.share.bill.dao.BillDao;
import com.share.bill.dao.GroupDao;
import com.share.bill.dao.UserDao;
import com.share.bill.dto.BillRequestDto;
import com.share.bill.entities.Bill;
import com.share.bill.entities.Contribution;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by prateekgupta on 09/09/17.
 */

@Component
public class ModestSplitwiseImpl implements ModestSplitwise {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BillDao billDao;

    @Autowired
    private GroupDao groupDao;

    @Transactional
    @Override
    public void addBill(BillRequestDto billRequestDto) {

        validations(billRequestDto);
//        Bill bill = new Bill(billRequestDto.getBillName(), billRequestDto.getAmount(), billRequestDto.getGrpId(),
//                billRequestDto.getUserContriPaid(), billRequestDto.getUserContriOwe());
//        billDao.persist(bill);
        if (billRequestDto.getGrpId() != null) {
            //updateUsersBalanceInGroup(billRequestDto.getGrpId(), bill);
        }
    }

    private void validations(BillRequestDto billRequestDto) {

        Double paidAmt = 0.0, owedAmt = 0.0, paidPer = 0.0, owedPer = 0.0;
        for(Map.Entry<User, Contribution> contributionEntry : billRequestDto.getUserContriPaid().entrySet()) {
            int cnt = 0;
            Group processGroup = groupDao.findById(billRequestDto.getGrpId());
//            for(User usr : processGroup.getUsers()) {
//                if(usr.equals(contributionEntry.getKey())) {
//                    cnt++;
//                    continue;
//                }
//            }
            if(cnt == 0) {
                System.out.println("User " + contributionEntry.getKey().getName() + " does not exists in this group");
                System.exit(0);
            }

            Map<User, Contribution> userContriOwe = billRequestDto.getUserContriOwe();
            if(contributionEntry.getValue().getShareAmount() == null && contributionEntry.getValue().getSharePercentage() == null
                    || userContriOwe.get(contributionEntry.getKey()).getShareAmount() == null && userContriOwe.get(contributionEntry.getKey()).getSharePercentage() == null) {
                System.out.println("No share data supplied");
                System.exit(0);
            } else {
                if (contributionEntry.getValue().getShareAmount() != null) {
                    paidAmt = paidAmt + contributionEntry.getValue().getShareAmount();
                } else {
                    paidPer = paidPer + contributionEntry.getValue().getSharePercentage();
                }
                if (userContriOwe.get(contributionEntry.getKey()).getShareAmount() != null) {
                    owedAmt = owedAmt + userContriOwe.get(contributionEntry.getKey()).getShareAmount();
                } else {
                    owedPer = owedPer + userContriOwe.get(contributionEntry.getKey()).getSharePercentage();
                }
            }
        }
        if(paidAmt.equals(billRequestDto.getAmount()) || paidPer.equals(new Double(100.0))
                && owedAmt.equals(billRequestDto.getAmount()) || owedPer.equals(new Double(100.0))) {

        } else {
            System.out.println("Incorrect paid or owed data supplied");
            System.exit(0);
        }
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
