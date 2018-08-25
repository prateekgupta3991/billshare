package com.share.bill.controllers;

import com.share.bill.dto.UserRequestDto;
import com.share.bill.dto.UserResponseDto;
import com.share.bill.entities.User;
import com.share.bill.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/v1/user")
public class UserController extends AbstractController {

	@Autowired
    private UserService userServiceImpl;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		return model;
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<User> createNewUser(@RequestBody UserRequestDto json ) {

		User usr = userServiceImpl.addNewUser(json);
		return new ResponseEntity<>(usr, HttpStatus.OK);
	}

	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<UserResponseDto>> getUser() {

		List<UserResponseDto> userList = userServiceImpl.getAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@RequestMapping(value="/{userid}", method=RequestMethod.GET)
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable(value = "userid") Long userId) {

        UserResponseDto user = userServiceImpl.getUser(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
