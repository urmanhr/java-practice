package com.urman.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urman.dao.IBmsTemplate;
import com.urman.hibernate.pojo.AccountInfo;
import com.urman.hibernate.pojo.CustomerPersonalInfo;

import net.sf.json.JSONObject;

@Component("testServiceHelper")
public class TestServiceHelper {

	@Autowired
	IBmsTemplate bmsTemplateImpl;

	public AccountInfo getAccountInfoFromJson(JSONObject jsonObject) {

		AccountInfo accountInfo = new AccountInfo();

		accountInfo.setAccountType(jsonObject.getString("account_type"));
		accountInfo.setActivationDate(new Date());
		CustomerPersonalInfo customerPersonalInfo = bmsTemplateImpl
				.getCustomerInfo(jsonObject.getString("customer_id"));
		accountInfo.setCustomerPersonalInfo(customerPersonalInfo);
		accountInfo.setIfscCode(jsonObject.getString("ifsc_code"));
		accountInfo.setInterest(Float.parseFloat(jsonObject.getString("interest")));
		accountInfo.setIntialDeposit(jsonObject.getLong("initial_deposit"));
		accountInfo.setRegistrationDate(new Date());

		return accountInfo;
	}

	public List<AccountInfo> getLstAccountInfo(List<Long> accountNumbers) {

		return bmsTemplateImpl.getLstAccountInfo(accountNumbers);
	}

	public AccountInfo getAccountInfo(Long accountNumber) {

		return bmsTemplateImpl.getAccountInfo(accountNumber);
	}

	public List<AccountInfo> getCustomerAccounts(String customerId) {

		return bmsTemplateImpl.getCustomerAccounts(customerId);
	}

	public void createAccount(AccountInfo accountInfo) {

		bmsTemplateImpl.createAccount(accountInfo);
	}

	public List<String> getAllCustomerIds() {
		
		return bmsTemplateImpl.getAllCustomerIds();
	}

}
