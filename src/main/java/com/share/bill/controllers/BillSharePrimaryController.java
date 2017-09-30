package com.share.bill.controllers;

import com.share.bill.dto.UserRequestDto;
import com.share.bill.entities.User;
import com.share.bill.services.BillShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;

@Controller
@RequestMapping(value="/v1")
public class BillSharePrimaryController extends AbstractController {

	@Autowired
    private BillShareService billShareServiceImpl;

	@RequestMapping(value="/user/new", method=RequestMethod.POST)
	public ResponseEntity<User> createNewUser(@RequestBody UserRequestDto json ) {

	    User usr = billShareServiceImpl.addNewUser(json);
		return new ResponseEntity<>(usr, HttpStatus.OK);
	}

	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getUser() {

        List<User> userList = billShareServiceImpl.getAllUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@Override
	protected ModelAndView handleRequestInternal(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		return model;
	}
}
