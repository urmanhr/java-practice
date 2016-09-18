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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urman.dao.IBmsTemplate;
import com.urman.hibernate.pojo.AccountInfo;
import com.urman.model.Model;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("u/form")
@Service
public class TestService {

	@Autowired
	IBmsTemplate bmsTemplateImpl;

	@GET
	@Path("/get1")
	@Produces(MediaType.APPLICATION_JSON)
	public Model testMethod() {

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

		List<AccountInfo> lstaccounts = null;
		try {
			JSONArray accountNumbers = jsonObject.getJSONArray("accountnumber");
			List<Long> accNumbers=new ArrayList<Long>();
			for (Object object : accountNumbers) {
				accNumbers.add(Long.parseLong(object.toString()));
			}
			lstaccounts = bmsTemplateImpl.getLstAccountInfo(accNumbers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(lstaccounts).build();
	}

}
