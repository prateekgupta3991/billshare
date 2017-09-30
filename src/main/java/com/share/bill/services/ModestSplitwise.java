package com.share.bill.services;

import com.share.bill.entities.Contribution;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;

import java.util.Map;

/**
 * Created by prateekgupta on 09/09/17.
 */
public interface ModestSplitwise {

    void addBill(String billName, Double amt, Group grp, Map<User, Contribution> userContriPaid, Map<User, Contribution> userContriOwe);

    void getGroupWiseUserBalance(User usr);

    void getTotalUserBalance(User usr);
}
