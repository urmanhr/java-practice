package com.urman.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urman.hibernate.pojo.AccountInfo;
import com.urman.hibernate.pojo.CustomerPersonalInfo;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

@Repository("bmsTemplateImpl")
public class BmsTemplateImpl implements IBmsTemplate {

	@Autowired
	SessionFactory sessionFactory;

	public static Logger LOGGER = Logger.getLogger(BmsTemplateImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountInfo> getLstAccountInfo(List<Long> accountNumbers) {

		List<AccountInfo> lstAccountInfo = null;
		Session session = sessionFactory.openSession();

		try {
			Criteria cr = session.createCriteria(AccountInfo.class);
			cr.add(Restrictions.in("accountNumber", accountNumbers));
			lstAccountInfo = cr.list();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return lstAccountInfo;
	}

	@Override
	public AccountInfo getAccountInfo(Long accountNumber) {
		AccountInfo accountInfo = null;
		Session session = sessionFactory.openSession();

		try {
			Criteria cr = session.createCriteria(AccountInfo.class);
			cr.add(Restrictions.eq("accountNumber", accountNumber));
			@SuppressWarnings("unchecked")
			List<AccountInfo> results = cr.list();
			accountInfo = results.get(0);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return accountInfo;
	}

	@Override
	public List<AccountInfo> getCustomerAccounts(String customerId) {

		List<AccountInfo> lstAccounts = null;
		Session session = sessionFactory.openSession();

		try {
			Criteria cr = session.createCriteria(CustomerPersonalInfo.class);
			cr.add(Restrictions.eq("customerId", customerId));
			@SuppressWarnings("unchecked")
			List<CustomerPersonalInfo> results = cr.list();
			CustomerPersonalInfo customerInfo = results.get(0);
			lstAccounts = customerInfo.getLstAccounts();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return lstAccounts;
	}

	@Override
	public CustomerPersonalInfo getCustomerInfo(String customerId) {

		CustomerPersonalInfo customerPersonalInfo = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria cr = session.createCriteria(CustomerPersonalInfo.class);
			cr.add(Restrictions.eq("customerId", customerId));
			@SuppressWarnings("unchecked")
			List<CustomerPersonalInfo> results = cr.list();
			customerPersonalInfo = results.get(0);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return customerPersonalInfo;
	}

	@Override
	public void createAccount(AccountInfo accountInfo) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(accountInfo);
			tx.commit();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	@Override
	public List<String> getAllCustomerIds() {

		List<String> customerIds=null;
		
		Session session = sessionFactory.openSession();
		try {
			Criteria cr = session.createCriteria(CustomerPersonalInfo.class);
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			@SuppressWarnings("unchecked")
			List<CustomerPersonalInfo> results = cr.list();
			customerIds=new ArrayList<String>();
			for (CustomerPersonalInfo customerPersonalInfo : results) {
				customerIds.add(customerPersonalInfo.getCustomerId());
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return customerIds;
	}

}
