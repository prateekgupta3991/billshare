package com.share.bill.services;

import com.share.bill.dto.GroupRequestDto;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prateek on 28/9/17.
 */
@Service
public interface BillShareService {

    User addNewUser(UserRequestDto userRequestDto);

    List<User> getAllUsers();

    User getUser(Long userId);

    Group addNewGroup(GroupRequestDto groupRequestDto);

    List<Group> getAllGroups();

    void addUserToGroup(GroupRequestDto groupRequestDto);
}
