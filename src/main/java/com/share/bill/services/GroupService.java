package com.share.bill.services;

import com.share.bill.dto.GroupRequestDto;
import com.share.bill.dto.GroupResponseDto;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.dto.UserResponseDto;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prateek on 28/9/17.
 */
public interface GroupService {

    Group addNewGroup(GroupRequestDto groupRequestDto);

    List<GroupResponseDto> getAllGroups();

    void addUserToGroup(GroupRequestDto groupRequestDto);
}
