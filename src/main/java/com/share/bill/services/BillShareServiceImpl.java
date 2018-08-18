package com.share.bill.services;

import com.share.bill.dao.BillDao;
import com.share.bill.dao.GroupDao;
import com.share.bill.dao.UserDao;
import com.share.bill.dto.*;
import com.share.bill.entities.*;
import com.share.bill.exceptions.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

/**
 * Created by prateek on 28/9/17.
 */
@Service
public class BillShareServiceImpl implements BillShareService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private BillDao billDao;

    @Autowired
    private GroupDao groupDao;

    @Override
    public User addNewUser(UserRequestDto userRequestDto) {

        User existingUser = userDao.findByEmail(userRequestDto.getEmail());
        if(existingUser != null) {
            throw new CustomerAlreadyExistsException("Customer with email id " + userRequestDto + " already exists");
        }
        User user = new User(userRequestDto.getName(), userRequestDto.getEmail());
        userDao.persist(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();
        return users;
    }

    @Override
    public Group addNewGroup(GroupRequestDto groupRequestDto) throws CustomerNotFoundException {
        if(groupRequestDto.getUserEmails() == null || groupRequestDto.getUserEmails().isEmpty()) {
            throw new GroupWithoutAdminException("User list is empty. Group without admin not possible!!!");
        }
        Group group = new Group(groupRequestDto.getName());
        groupDao.persist(group);
        return group;
    }

    @Override
    public List<Group> getAllGroups() throws CustomerNotFoundException {
        return groupDao.findAll();
    }

    private void verifyUserList(List<String> emails) {

        List<User> userList = userDao.findAllByEmail(emails);
        if (userList.size() == emails.size()) {
            throw new CustomerNotFoundException("Some customers does not exists");
        }
    }

    @Override
    public void addUserToGroup(GroupRequestDto groupRequestDto) throws GroupNotFoundException, CustomerNotFoundException {

        verifyGroupExists(groupRequestDto.getId());
        verifyUserList(groupRequestDto.getUserEmails());
        // add users to database
    }

    private void verifyGroupExists(Long id) {

        Group grp = groupDao.findById(id);
        if(grp == null) {
            throw new GroupNotFoundException("Group with id " + id + " does not exists");
        }
    }
}
