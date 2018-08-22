package com.share.bill.controllers;

import com.share.bill.dto.GroupRequestDto;
import com.share.bill.dto.GroupResponseDto;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.entities.Group;
import com.share.bill.entities.User;
import com.share.bill.services.BillShareService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;

@Controller
@RequestMapping(value="/v1")
public class BillSharePrimaryController extends AbstractController {

	@Autowired
    private BillShareService billShareServiceImpl;

	public void setBillShareServiceImpl(BillShareService billShareServiceImpl) {
		this.billShareServiceImpl = billShareServiceImpl;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		return model;
	}

	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<User> createNewUser(@RequestBody UserRequestDto json ) {

		User usr = billShareServiceImpl.addNewUser(json);
		return new ResponseEntity<>(usr, HttpStatus.OK);
	}

	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getUser() {

		List<User> userList = billShareServiceImpl.getAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@RequestMapping(value="/user/{userid}", method=RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable(value = "userid") Long userId) {

		User user = billShareServiceImpl.getUser(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value="/group", method=RequestMethod.POST)
	public ResponseEntity<Group> createNewGroup(@RequestBody GroupRequestDto json ) {

		Group group = billShareServiceImpl.addNewGroup(json);
		return new ResponseEntity<>(group, HttpStatus.OK);
	}

	@RequestMapping(value="/group", method=RequestMethod.GET)
	public ResponseEntity<List<GroupResponseDto>> getGroups() {

        List<GroupResponseDto> groupList = billShareServiceImpl.getAllGroups();
		return new ResponseEntity<>(groupList, HttpStatus.OK);
	}

	@RequestMapping(value="/group/addUsers", method=RequestMethod.PUT)
	public ResponseEntity<Group> addUsersToGroup(@RequestBody GroupRequestDto groupRequestDto) {

		billShareServiceImpl.addUserToGroup(groupRequestDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
