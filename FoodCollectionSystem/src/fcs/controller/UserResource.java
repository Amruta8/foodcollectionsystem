package fcs.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fc.provider.ConnectionProvider;
import fc.services.UserServices;
import fcs.bean.User;

@Path("/user")
public class UserResource {
	@POST
	@Path("signUp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String registerUser(User user){
		return "success";
	}
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public User loginAdmin(@QueryParam("name") String name, @QueryParam("password") String password){
		UserServices userServices = new UserServices();
		System.out.println(name+"###"+password);
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setPassword(userServices.loginAdmin(user));
		return user;
	}
	@POST
	@Path("signUp")
	@Consumes(MediaType.APPLICATION_JSON)
	public String signUpUser(User user){
		System.out.println("Into Signup method");
		System.out.println(user.toString());
		return user.getName();
	}
}
