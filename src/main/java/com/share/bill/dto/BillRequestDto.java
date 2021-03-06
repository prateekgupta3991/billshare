package com.share.bill.dto;

import com.share.bill.entities.Contribution;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String billName;

	private Double amount;

	private Long grpId;

	private Map<Long, Contribution> userContriPaid;

	private Map<Long, Contribution> userContriOwe;

	public BillRequestDto() {
	}

	public BillRequestDto(String billName, Double amount, Long grpId) {
		this.billName = billName;
		this.amount = amount;
		this.grpId = grpId;
	}

	public BillRequestDto(String billName, Double amount, Long grpId, Map<Long, Contribution> userContriPaid, Map<Long, Contribution> userContriOwe) {
		this.billName = billName;
		this.amount = amount;
		this.grpId = grpId;
		this.userContriPaid = userContriPaid;
		this.userContriOwe = userContriOwe;
	}

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

	public Map<Long, Contribution> getUserContriPaid() {
		return userContriPaid;
	}

	public void setUserContriPaid(Map<Long, Contribution> userContriPaid) {
		this.userContriPaid = userContriPaid;
	}

	public Map<Long, Contribution> getUserContriOwe() {
		return userContriOwe;
	}

	public void setUserContriOwe(Map<Long, Contribution> userContriOwe) {
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
