package com.share.bill.controllers;

import com.share.bill.dto.GroupRequestDto;
import com.share.bill.dto.GroupResponseDto;
import com.share.bill.entities.Group;
import com.share.bill.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/v1/group")
public class GroupController extends AbstractController {

	@Autowired
    private GroupService groupServiceImpl;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		return model;
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Group> createNewGroup(@RequestBody GroupRequestDto json ) {

		Group group = groupServiceImpl.addNewGroup(json);
		return new ResponseEntity<>(group, HttpStatus.OK);
	}

	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<GroupResponseDto>> getGroups() {

        List<GroupResponseDto> groupList = groupServiceImpl.getAllGroups();
		return new ResponseEntity<>(groupList, HttpStatus.OK);
	}

	@RequestMapping(value="/addUsers", method=RequestMethod.PUT)
	public ResponseEntity<Group> addUsersToGroup(@RequestBody GroupRequestDto groupRequestDto) {

		groupServiceImpl.addUserToGroup(groupRequestDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
