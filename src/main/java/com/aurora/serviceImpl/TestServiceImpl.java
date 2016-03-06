package com.aurora.serviceImpl;

import javax.ws.rs.Path;
import com.aurora.service.TestService;

@Path("/test")
public class TestServiceImpl implements TestService{

	public String getMsg(String msg) {
		String output = "Jersey say : " + msg;
		//return Response.status(200).entity(output).build();
		return output;
	}

}
