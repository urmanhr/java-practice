package com.urman.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.urman.hibernate.pojo.AccountInfo;
import com.urman.model.Model;
import com.urman.util.BaseException;
import com.urman.util.TestServiceHelper;

import net.sf.json.JSONObject;

@Path("u/form")
@Service
public class TestService {

	@Autowired
	TestServiceHelper testServiceHelper;

	public static Logger LOGGER = Logger.getLogger(TestService.class);

	@GET
	@Path("/get1")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testMethod() {
		LOGGER.setLevel(Level.ALL);
		List<Long> accountNumbers = new ArrayList<Long>();
		Model model = null;
		try {
			accountNumbers.add(1234567898765432L);
			accountNumbers.add(1234567898765433L);
			List<AccountInfo> lstAccounts = testServiceHelper.getLstAccountInfo(accountNumbers);
			String s = lstAccounts.get(0).getAccountType();
			model = new Model("urman", s, 25);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			JSONObject error=new JSONObject();
			error.put("error", e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		return Response.status(Response.Status.OK).entity(model).build();
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

			accountInfo = testServiceHelper.getAccountInfo(accountNumber);
			if (null == accountInfo) {
				throw new BaseException("accounts not found");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			JSONObject error=new JSONObject();
			error.put("error", e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}
		return Response.status(Response.Status.OK).entity(accountInfo).build();
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

			lstaccounts = testServiceHelper.getCustomerAccounts(customerId);
			if (null == lstaccounts || CollectionUtils.isEmpty(lstaccounts)) {
				throw new BaseException("accounts not found");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			JSONObject error=new JSONObject();
			error.put("error", e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();

		}
		return Response.status(Response.Status.OK).entity(lstaccounts).build();
	}

	@POST
	@Path("/createAccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCustomerAccountInfo(JSONObject jsonObject) {
		LOGGER.setLevel(Level.ALL);
		AccountInfo accountInfo = null;

		try {
			accountInfo = testServiceHelper.getAccountInfoFromJson(jsonObject);
			testServiceHelper.createAccount(accountInfo);

		} catch (Exception e) {
			LOGGER.error("could not create account because of " + e.getMessage(), e);
			JSONObject error=new JSONObject();
			error.put("error", e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();

		}
		return Response.status(Response.Status.CREATED).entity(jsonObject).build();
	}

	@GET
	@Path("/getCustomerIds")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerIds() {
		LOGGER.setLevel(Level.ALL);
		List<String> customerIds = null;
		try {
			customerIds = testServiceHelper.getAllCustomerIds();
		} catch (Exception e) {
			LOGGER.error("could not create account because of " + e.getMessage(), e);
			JSONObject error=new JSONObject();
			error.put("error", e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		return Response.status(Response.Status.OK).entity(customerIds).build();
	}

}
