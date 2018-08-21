package com.share.bill.services;

import com.share.bill.dao.BillDao;
import com.share.bill.dao.GroupDao;
import com.share.bill.dao.UserDao;
import com.share.bill.dto.GroupRequestDto;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;
import com.share.bill.exceptions.CustomerAlreadyExistsException;
import com.share.bill.exceptions.CustomerNotFoundException;
import com.share.bill.exceptions.GroupNotFoundException;
import com.share.bill.exceptions.GroupWithoutAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();
        return users;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(Long userId) {
        User user = userDao.findById(userId);
        return user;
    }

    @Transactional
    @Override
    public Group addNewGroup(GroupRequestDto groupRequestDto) throws CustomerNotFoundException {
        if(groupRequestDto.getUserEmails() == null || groupRequestDto.getUserEmails().isEmpty()) {
            throw new GroupWithoutAdminException("User list is empty. Group without admin not possible!!!");
        }
        List<User> members = userDao.findAllByEmail(groupRequestDto.getUserEmails());
        User admin = members.get(0);
        Group group = new Group(groupRequestDto.getName(), members, admin);
        groupDao.persist(group);
        return group;
    }

    @Transactional(readOnly = true)
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

    @Transactional
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
