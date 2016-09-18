package com.urman.dao;

import java.util.List;

import com.urman.hibernate.pojo.AccountInfo;
import com.urman.hibernate.pojo.CustomerPersonalInfo;

public interface IBmsTemplate {
	
	List<AccountInfo> getLstAccountInfo(List<Long> accountNumbers);

	AccountInfo getAccountInfo(Long accountNumber);

	List<AccountInfo> getCustomerAccounts(String customerId);

	CustomerPersonalInfo getCustomerInfo(String customerId);

	void createAccount(AccountInfo accountInfo);

	List<String> getAllCustomerIds();

}
