package com.share.bill.controllers;

import com.share.bill.dto.BillRequestDto;
import com.share.bill.dto.UserRequestDto;
import com.share.bill.dto.UserResponseDto;
import com.share.bill.entities.User;
import com.share.bill.services.BillService;
import com.share.bill.services.GroupService;
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
@RequestMapping(value="/v1/bill")
public class BillController extends AbstractController {

	@Autowired
    private BillService billServiceImpl;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
		return model;
	}

	@RequestMapping(value="/group", method=RequestMethod.POST)
	public ResponseEntity<?> addNewBillToGroup(@RequestBody BillRequestDto billRequestDto) {

		billServiceImpl.addBillToGroup(billRequestDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<?> addNewBill(@RequestBody BillRequestDto billRequestDto) {

		billServiceImpl.addBill(billRequestDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{billId}", method=RequestMethod.GET)
	public ResponseEntity<BillRequestDto> getBill(@PathVariable(value = "billId") Long billId) {

		BillRequestDto billResponse = billServiceImpl.getBillDetails(billId);
		return new ResponseEntity<>(billResponse, HttpStatus.OK);
	}
}
