package com.share.bill.services;

import com.share.bill.entities.Bill;
import com.share.bill.entities.Contribution;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prateekgupta on 09/09/17.
 */
public class ModestSplitwiseImpl implements ModestSplitwise {

    private Map<Group, Bill> groupBillMap;

    public ModestSplitwiseImpl() {
        super();
        groupBillMap = new HashMap<>();
    }

    public ModestSplitwiseImpl(Map<Group, Bill> listOfBills) {
        this.groupBillMap = listOfBills;
    }

    @Override
    public void addBill(String billName, Double amt, Group grp, Map<User, Contribution> userContriPaid, Map<User, Contribution> userContriOwe) {

        validations(amt, grp, userContriPaid, userContriOwe);
        Bill bill = new Bill(billName, amt, grp, userContriPaid, userContriOwe);
        groupBillMap.put(grp, bill);
        addBillToGroup(grp, bill);
        updateUsersBalanceInGroup(grp, bill);
    }

    private void validations(Double amt, Group grp, Map<User, Contribution> userContriPaid, Map<User, Contribution> userContriOwe) {

        Double paidAmt = 0.0, owedAmt = 0.0, paidPer = 0.0, owedPer = 0.0;
        for(Map.Entry<User, Contribution> contributionEntry : userContriPaid.entrySet()) {
            int cnt = 0;
            for(User usr : grp.getUsers()) {
                if(usr.equals(contributionEntry.getKey())) {
                    cnt++;
                    continue;
                }
            }
            if(cnt == 0) {
                System.out.println("User " + contributionEntry.getKey().getName() + " does not exists in this group");
                System.exit(0);
            }

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
        if(paidAmt.equals(amt) || paidPer.equals(new Double(100.0)) && owedAmt.equals(amt) || owedPer.equals(new Double(100.0))) {

        } else {
            System.out.println("Incorrect paid or owed data supplied");
            System.exit(0);
        }
    }

    private void updateUsersBalanceInGroup(Group grp, Bill bill) {
        List<User> grpUsers = grp.getUsers();
        for(User userItr : grpUsers) {
            Contribution currentUserContriPaid = bill.getUserContributions().get(userItr);
            Contribution currentUserContriOwe = bill.getUserOwed().get(userItr);

            Double sharePaid = getUserPaidAmount(currentUserContriPaid, bill.getBillAmount());
            Double shareOwe = getUserOweAmount(currentUserContriOwe, bill.getBillAmount());

            Double netAmt = sharePaid - shareOwe;
            Double grpUserAmt = userItr.getGroupWiseAmount().get(grp);
            Double newGrpUserAmt = null;
            if(grpUserAmt != null)
                newGrpUserAmt = netAmt + grpUserAmt;
            else
                newGrpUserAmt = netAmt;
            userItr.getGroupWiseAmount().put(grp, newGrpUserAmt);

            Double userTotalAmt = userItr.getTotalAmount();
            if(userTotalAmt != null)
                userItr.setTotalAmount(userTotalAmt + newGrpUserAmt);
            else
                userItr.setTotalAmount(newGrpUserAmt);
        }
    }

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

    private void addBillToGroup(Group grp, Bill bill) {
        grp.getBills().add(bill);
    }

    @Override
    public void getGroupWiseUserBalance(User usr) {
        for(Map.Entry<Group, Double> userGrpToBalance : usr.getGroupWiseAmount().entrySet()) {
            System.out.println("Group : " + userGrpToBalance.getKey().getName() + " Balance : " + userGrpToBalance.getValue());
        }
    }

    @Override
    public void getTotalUserBalance(User usr) {
        System.out.println(usr.getTotalAmount());
    }
}
