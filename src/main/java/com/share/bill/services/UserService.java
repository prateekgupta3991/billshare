package com.share.bill.services;

import com.share.bill.dto.UserRequestDto;
import com.share.bill.dto.UserResponseDto;
import com.share.bill.entities.User;

import java.util.List;

/**
 * Created by prateek on 28/9/17.
 */
public interface UserService {

    User addNewUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUser(Long userId);
}
