package com.share.bill.services;

import com.share.bill.dto.GroupRequestDto;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.entities.Bill;
import com.share.bill.entities.Group;

import com.share.bill.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

/**
 * Created by prateek on 28/9/17.
 */
@Service
public class BillShareServiceImpl implements BillShareService{

    List<User> userList = new ArrayList<>();
    List<Group> groupList = new ArrayList<>();
    List<Bill> billList = new ArrayList<>();

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

    @Override
    public Group addNewGroup(GroupRequestDto groupRequestDto) {
        Group group = new Group(groupRequestDto.getName());
        groupList.add(group);
        return group;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupList;
    }
}
