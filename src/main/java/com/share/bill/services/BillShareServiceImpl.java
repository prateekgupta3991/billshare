package com.share.bill.services;

import com.share.bill.dto.*;
import com.share.bill.entities.*;
import com.share.bill.exceptions.*;
import java.util.*;
import org.springframework.stereotype.*;

/**
 * Created by prateek on 28/9/17.
 */
@Service
public class BillShareServiceImpl implements BillShareService{

    private List<User> userList = new ArrayList<>();
    private List<Group> groupList = new ArrayList<>();
    private List<Bill> billList = new ArrayList<>();

    private Map<String, User> emailToUserMap = new HashMap<>();
    private Map<Long, Group> groupIdToGroupMap = new HashMap<>();

    private Map<User, List<Group>> userGroupListMap = new HashMap<>();
    private Map<Group, List<User>> groupUserListMap = new HashMap<>();
    private Map<Group, Bill> groupBillMap = new HashMap<>();

    private Long identityCtr = 1L;

    @Override
    public User addNewUser(UserRequestDto userRequestDto) {

        if(emailToUserMap.containsKey(userRequestDto.getEmail().toLowerCase())) {
            throw new CustomerAlreadyExistsException("Customer with email id " + userRequestDto + " already exists");
        }
        User user = new User(userRequestDto.getName(), userRequestDto.getEmail());
        emailToUserMap.put(userRequestDto.getEmail().toLowerCase(), user);
        userList.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public Group addNewGroup(GroupRequestDto groupRequestDto) throws CustomerNotFoundException {
        if(groupRequestDto.getUserRequestDtoList() == null || groupRequestDto.getUserRequestDtoList().isEmpty()) {
            throw new GroupWithoutAdminException("User list is empty. Group without admin not possible!!!");
        }
        Group group = new Group(groupRequestDto.getName());
        groupList.add(group);

        // considers 0th element of the userlist to be admin by default
        List<User> userList = convertUserDtoToUser(groupRequestDto.getUserRequestDtoList());
        verifyUserList(userList);
        List<User> admin = new ArrayList<>();
        admin.add(userList.get(0));
        group.setAdmins(admin);
        group.setUsers(userList);
        group.setId(identityCtr);
        identityCtr++;
        groupIdToGroupMap.put(group.getId(), group);
        groupUserListMap.put(group, userList);

        return group;
    }

    private List<User> convertUserDtoToUser(List<UserRequestDto> userList) {

        List<User> userListFromDto = new ArrayList<>();
        for(UserRequestDto userRequestDto : userList) {
            User usr = emailToUserMap.containsKey(userRequestDto.getEmail().toLowerCase()) == true ? emailToUserMap
                .get(userRequestDto.getEmail().toLowerCase()) : new User(userRequestDto.getName(), userRequestDto.getEmail());
            userListFromDto.add(usr);
        }
        return userListFromDto;
    }

    private void verifyUserList(List<User> userList) {
        List<User> nonExistentUsers = new ArrayList<>();
        for(User usr : userList) {
            if(!emailToUserMap.containsKey(usr.getEmail().toLowerCase())) {
                nonExistentUsers.add(usr);
            }
        }
        if(!nonExistentUsers.isEmpty()) {
            throw new CustomerNotFoundException("Customer does not exists " + nonExistentUsers);
        }
    }

    @Override
    public List<Group> getAllGroups() {
        return groupList;
    }

    @Override
    public void addUserToGroup(GroupRequestDto groupRequestDto) throws GroupNotFoundException,
        CustomerNotFoundException {

        verifyGroupExists(groupRequestDto);
        List<User> userList = convertUserDtoToUser(groupRequestDto.getUserRequestDtoList());
        verifyUserList(userList);
        List<User> users = groupUserListMap.get(groupIdToGroupMap.get(groupRequestDto.getId()));
        users.addAll(userList);
    }

    private void verifyGroupExists(GroupRequestDto groupRequestDto) {
        if(!groupIdToGroupMap.containsKey(groupRequestDto.getId())) {
            throw new GroupNotFoundException("Group " + groupRequestDto.getName() + " does not exists");
        }
    }
}
