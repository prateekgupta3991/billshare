package com.share.bill.dto;

import com.share.bill.entities.Contribution;
import com.share.bill.entities.User;

import java.io.Serializable;
import java.util.Map;

public class BillRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String billName;

	private Double amount;

	private Long grpId;

	private Map<User, Contribution> userContriPaid;

	private Map<User, Contribution> userContriOwe;

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getGrpId() {
		return grpId;
	}

	public void setGrpId(Long grpId) {
		this.grpId = grpId;
	}

	public Map<User, Contribution> getUserContriPaid() {
		return userContriPaid;
	}

	public void setUserContriPaid(Map<User, Contribution> userContriPaid) {
		this.userContriPaid = userContriPaid;
	}

	public Map<User, Contribution> getUserContriOwe() {
		return userContriOwe;
	}

	public void setUserContriOwe(Map<User, Contribution> userContriOwe) {
		this.userContriOwe = userContriOwe;
	}

	@Override
	public String toString() {
		return "BillRequestDto{" +
				"billName='" + billName + '\'' +
				", amount=" + amount +
				", grpId=" + grpId +
				", userContriPaid=" + userContriPaid +
				", userContriOwe=" + userContriOwe +
				'}';
	}
}
