package com.urman.hibernate.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="account_info")
public class AccountInfo {

	@Id
	@Column(name="account_no")
	private Long accountNumber;
	
	/*@Column(name="customer_id")
	private String customerId;*/
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="registration_date")
	private Date registrationDate;
	
	@Column(name="activation_date")
	private Date activationDate;
	
	@Column(name="ifsc_code")
	private String ifscCode;
	
	@Column(name="interest")
	private Integer interest;
	
	@Column(name="intial_deposit")
	private Long intialDeposit;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="customer_id")
	CustomerPersonalInfo customerPersonalInfo;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	/*public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}*/

	public String getAccountType() {
		return accountType;
	}

	public CustomerPersonalInfo getCustomerPersonalInfo() {
		return customerPersonalInfo;
	}

	public void setCustomerPersonalInfo(CustomerPersonalInfo customerPersonalInfo) {
		this.customerPersonalInfo = customerPersonalInfo;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Integer getInterest() {
		return interest;
	}

	public void setInterest(Integer interest) {
		this.interest = interest;
	}

	public Long getIntialDeposit() {
		return intialDeposit;
	}

	public void setIntialDeposit(Long intialDeposit) {
		this.intialDeposit = intialDeposit;
	}

	@Override
	public String toString() {
		return "AccountInfo [accountNumber=" + accountNumber + ", accountType=" + accountType + ", registrationDate="
				+ registrationDate + ", activationDate=" + activationDate + ", ifscCode=" + ifscCode + ", interest="
				+ interest + ", intialDeposit=" + intialDeposit + "]";
	}
	
	
	
	
	
}
