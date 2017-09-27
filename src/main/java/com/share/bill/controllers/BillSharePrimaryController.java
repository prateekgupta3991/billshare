package com.share.bill.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.bill.dto.UserRequestDto;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
@RequestMapping(value="/v1")
public class BillSharePrimaryController extends AbstractController {
	
	@RequestMapping(value="/user/new", method=RequestMethod.POST)
	public ResponseEntity<UserRequestDto> createNewUser(@RequestBody UserRequestDto json ) {
		System.out.println(json+" received");
		return new ResponseEntity<UserRequestDto>(json, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<?> getUser() {
		System.out.println("Hey man received");
		return null;
	}

	@Override
	protected ModelAndView handleRequestInternal(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");

		return model;
	}
}
