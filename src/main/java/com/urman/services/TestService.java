package com.urman.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.urman.dao.IBmsTemplate;
import com.urman.hibernate.pojo.AccountInfo;
import com.urman.hibernate.pojo.CustomerPersonalInfo;
import com.urman.model.Model;
import com.urman.util.BaseException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("u/form")
@Service
public class TestService {

	@Autowired
	IBmsTemplate bmsTemplateImpl;

	public static Logger LOGGER = Logger.getLogger(TestService.class);

	@GET
	@Path("/get1")
	@Produces(MediaType.APPLICATION_JSON)
	public Model testMethod() {
		LOGGER.setLevel(Level.ALL);
		List<Long> accountNumbers = new ArrayList<Long>();
		accountNumbers.add(1234567898765432L);
		accountNumbers.add(1234567898765433L);
		List<AccountInfo> lstAccounts = bmsTemplateImpl.getLstAccountInfo(accountNumbers);
		String s = lstAccounts.get(0).getAccountType();
		Model model = new Model("urman", s, 25);

		return model;
	}

	@POST
	@Path("/accountinfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response showAccountInfo(JSONObject jsonObject) {
		LOGGER.setLevel(Level.ALL);
		AccountInfo accountInfo = null;
		try {
			Long accountNumber = jsonObject.getLong("accountnumber");

			accountInfo = bmsTemplateImpl.getAccountInfo(accountNumber);
			if (null == accountInfo) {
				throw new BaseException("accounts not found");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return Response.status(200).entity(accountInfo).build();
	}

	@POST
	@Path("/customeracc")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response showCustomerAccountInfo(JSONObject jsonObject) {
		LOGGER.setLevel(Level.ALL);
		List<AccountInfo> lstaccounts = null;
		try {
			String customerId = jsonObject.getString("customerid");

			lstaccounts = bmsTemplateImpl.getCustomerAccounts(customerId);
			if (null == lstaccounts || CollectionUtils.isEmpty(lstaccounts)) {
				throw new BaseException("accounts not found");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());

		}
		return Response.status(200).entity(lstaccounts).build();
	}

	@PUT
	@Path("/createAccount")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomerAccountInfo(JSONObject jsonObject) {
		LOGGER.setLevel(Level.ALL);
		AccountInfo accountInfo = null;

		try {
			accountInfo = getAccountInfoFromJson(jsonObject);
			bmsTemplateImpl.createAccount(accountInfo);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());

		}
		return null;
	}

	@GET
	@Path("/getCustomerIds")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getCustomerIds() {
		LOGGER.setLevel(Level.ALL);
		
		List<String> customerIds=bmsTemplateImpl.getAllCustomerIds();
		

		return customerIds;
	}
	
	private AccountInfo getAccountInfoFromJson(JSONObject jsonObject) {

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

}
