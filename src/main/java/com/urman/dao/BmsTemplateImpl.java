package com.urman.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.urman.hibernate.pojo.AccountInfo;

import org.hibernate.Criteria;
import org.hibernate.Session;

@Repository("bmsTemplateImpl")
public class BmsTemplateImpl implements IBmsTemplate{
	
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountInfo> getLstAccountInfo(List<Long> accountNumbers) {


		List<AccountInfo> lstAccountInfo=null;
		Session session=sessionFactory.openSession();
		
		try{
			Criteria cr=session.createCriteria(AccountInfo.class);
			cr.add(Restrictions.in("accountNumber", accountNumbers));
			lstAccountInfo=cr.list();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lstAccountInfo;
	}

	@Override
	public AccountInfo getAccountInfo(Long accountNumber) {
		AccountInfo accountInfo=null;
		Session session=sessionFactory.openSession();
		
		try{
			Criteria cr=session.createCriteria(AccountInfo.class);
			cr.add(Restrictions.eq("accountNumber", accountNumber));
			@SuppressWarnings("unchecked")
			List<AccountInfo> results=cr.list();
			accountInfo=results.get(0);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return accountInfo;
	}

	
	
	
}