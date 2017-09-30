package com.share.bill.services;

import com.share.bill.User;
import com.share.bill.dto.UserRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by prateek on 28/9/17.
 */
@Service
public interface BillShareService {

    User addNewUser(UserRequestDto userRequestDto);

    List<User> getAllUsers();
}
