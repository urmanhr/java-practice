package com.urman.dao;

import java.util.List;

import com.urman.hibernate.pojo.AccountInfo;

public interface IBmsTemplate {
	
	List<AccountInfo> getLstAccountInfo(List<Long> accountNumbers);

	AccountInfo getAccountInfo(Long accountNumber);

	List<AccountInfo> getcustomerAccounts(String customerId);

}
