package com.share.bill.services;

import com.share.bill.Bill;
import com.share.bill.Group;
import com.share.bill.User;
import com.share.bill.dto.UserRequestDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prateek on 28/9/17.
 */
public class BillShareServiceImpl implements BillShareService{

    List<User> userList = new ArrayList<>();
    List<User> groupList = new ArrayList<>();
    List<User> billList = new ArrayList<>();

    Map<User, List<Group>> userGroupListMap = new HashMap<>();
    Map<Group, List<User>> groupUserListMap = new HashMap<>();
    Map<Group, Bill> groupBillMap = new HashMap<>();

    @Override
    public User addNewUser(UserRequestDto userRequestDto) {

        User user = new User(userRequestDto.getName());
        userList.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }
}
