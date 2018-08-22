package com.share.bill.services;

import com.share.bill.dto.BillRequestDto;
import com.share.bill.entities.Contribution;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by prateekgupta on 09/09/17.
 */

@Component
public interface ModestSplitwise {

    void addBill(BillRequestDto billRequestDto);

//    void getGroupWiseUserBalance(User usr);
//
//    void getTotalUserBalance(User usr);
}
