package com.urman.hibernate.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

@Entity
@Table(name="customer_personal_info")
public class CustomerPersonalInfo {
	
	@Id
	@Column(name="customer_id")
	String customerId;
	
	@Column(name="customer_name")
	String customerName;
	
	@Column(name="date_of_birth")
	Date dateOfBirth;
	
	@Column(name="guardian_name")
	String guardianName;
	
	@Column(name="address")
	String address;
	
	@Column(name="contact_no")
	Long contactNo;
	
	@Column(name="mail_id")
	String mailId;
	
	@Column(name="gender")
	String gender;
	
	@Column(name="marital_status")
	String maritalStatus;
	
	@Column(name="identification_doc_type")
	String idDocType;
	
	@Column(name="id_doc_no")
	String idDocNo;
	
	@Column(name="citizenship")
	String citizenship;
	
	@OneToMany(mappedBy="customerPersonalInfo")
	List<AccountInfo> lstAccounts;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getIdDocType() {
		return idDocType;
	}

	public void setIdDocType(String idDocType) {
		this.idDocType = idDocType;
	}

	public String getIdDocNo() {
		return idDocNo;
	}

	public void setIdDocNo(String idDocNo) {
		this.idDocNo = idDocNo;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public List<AccountInfo> getLstAccounts() {
		return lstAccounts;
	}

	public void setLstAccounts(List<AccountInfo> lstAccounts) {
		this.lstAccounts = lstAccounts;
	}

	@Override
	public String toString() {
		return "CustomerPersonalInfo [customerId=" + customerId + ", customerName=" + customerName + ", dateOfBirth="
				+ dateOfBirth + ", guardianName=" + guardianName + ", address=" + address + ", contactNo=" + contactNo
				+ ", mailId=" + mailId + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", idDocType="
				+ idDocType + ", idDocNo=" + idDocNo + ", citizenship=" + citizenship +"]";
	}
	
	
	
	
}
