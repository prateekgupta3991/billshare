package com.share.bill.services;

import com.share.bill.dao.BillDao;
import com.share.bill.dao.GroupDao;
import com.share.bill.dao.UserDao;
import com.share.bill.dao.UserGroupDao;
import com.share.bill.dto.GroupRequestDto;
import com.share.bill.dto.GroupResponseDto;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.dto.UserResponseDto;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;
import com.share.bill.entities.UserGroup;
import com.share.bill.exceptions.CustomerAlreadyExistsException;
import com.share.bill.exceptions.CustomerNotFoundException;
import com.share.bill.exceptions.GroupNotFoundException;
import com.share.bill.exceptions.GroupWithoutAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserGroupDao userGroupDao;

    @Transactional
    @Override
    public User addNewUser(UserRequestDto userRequestDto) {

        User existingUser = userDao.findByEmail(userRequestDto.getEmail());
        if(existingUser != null) {
            throw new CustomerAlreadyExistsException("Customer with email id " + userRequestDto + " already exists");
        }
        User user = new User(userRequestDto.getName(), userRequestDto.getEmail(), userRequestDto.getContact());
        userDao.persist(user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userDao.findAll();
        List<UserResponseDto> dtoList = new ArrayList<>();
        for (User usr : users) {
            UserResponseDto dto = new UserResponseDto(usr.getName(), usr.getEmail(), usr.getContact());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto getUser(Long userId) {
        User user = userDao.findById(userId);
        UserResponseDto userResponseDto = new UserResponseDto(user.getName(), user.getEmail(), user.getContact());
        return userResponseDto;
    }

    @Transactional
    @Override
    public Group addNewGroup(GroupRequestDto groupRequestDto) throws CustomerNotFoundException {
        if(groupRequestDto.getUserEmails() == null || groupRequestDto.getUserEmails().isEmpty()) {
            throw new GroupWithoutAdminException("User list is empty. Group without admin not possible!!!");
        }
        List<User> members = userDao.findAllByEmail(groupRequestDto.getUserEmails());
        User admin = members.get(0);

        Group group = new Group(groupRequestDto.getName(), admin);
        groupDao.persist(group);
        for (User user : members) {
            UserGroup usrGrp = new UserGroup(user);
            usrGrp.setGang(group);
            userGroupDao.persist(usrGrp);
        }
        return group;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GroupResponseDto> getAllGroups() throws CustomerNotFoundException {
        List<Group> groups = groupDao.findAll();
        List<GroupResponseDto> groupsList = convertEntityToDto(groups);
        return groupsList;
    }

    private List<GroupResponseDto> convertEntityToDto(List<Group> groups) {
        List<GroupResponseDto> dtos = new ArrayList<>();
        for (Group grp : groups) {
            GroupResponseDto dto = new GroupResponseDto();
            dto.setId(grp.getId());
            UserResponseDto adminDto = new UserResponseDto(grp.getAdminUser().getName(), grp.getAdminUser().getEmail(), grp.getAdminUser().getContact());
            dto.setAdmin(adminDto);
            dto.setName(grp.getName());
            List<UserResponseDto> userList = new ArrayList<>();
            for (UserGroup usrgrp : grp.getUserGroups()) {
                UserResponseDto responseDto = new UserResponseDto(usrgrp.getUser().getName(), usrgrp.getUser().getEmail(), usrgrp.getUser().getContact());
                userList.add(responseDto);
            }
            dto.setUsers(userList);
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    @Override
    public void addUserToGroup(GroupRequestDto groupRequestDto) throws GroupNotFoundException, CustomerNotFoundException {

        Group grp = groupDao.findById(groupRequestDto.getId());
        if(grp == null) {
            throw new GroupNotFoundException("Group with id " + groupRequestDto.getId() + " does not exists");
        }
        List<User> userList = userDao.findAllByEmail(groupRequestDto.getUserEmails());
        if (userList.size() != groupRequestDto.getUserEmails().size()) {
            throw new CustomerNotFoundException("Some customers does not exists");
        }

        // add users to database
        List<UserGroup> userGroupList = new ArrayList<>();
        for (User usr : userList) {
            UserGroup userGroup = new UserGroup(grp, usr);
            userGroupList.add(userGroup);
        }
        userGroupDao.persistAll(userGroupList);
    }
}
