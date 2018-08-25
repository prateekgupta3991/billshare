package com.share.bill.services;

import com.share.bill.dao.BillDao;
import com.share.bill.dao.GroupDao;
import com.share.bill.dao.UserDao;
import com.share.bill.dao.UserGroupDao;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.dto.UserResponseDto;
import com.share.bill.entities.User;
import com.share.bill.exceptions.CustomerAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prateek on 28/9/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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
}
