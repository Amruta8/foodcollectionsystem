package fcs.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fc.services.UserServices;
import fcs.bean.User;

@Path("/user")
public class UserResource {
	
	UserServices userServices;
	public UserResource() {
		userServices = new UserServices();
	}
		
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
		System.out.println(name+"###"+password);
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setPassword(userServices.loginAdmin(user));
		return user;
	}
	@GET
	@Path("signUp")
	public String signUpUser(@QueryParam("name") String name,@QueryParam("email") String email,@QueryParam("mobile") String mobile,@QueryParam("password") String password){
		User user = new User(name, email, mobile, "", "", password);
		//userServices.signUpUser(user);
		System.out.println("Into Signup method");
		System.out.println(user.toString());
		return userServices.signUpUser(user);
	}
	
	@GET
	@Path("checkLogin")
	public String credentialsCheck(@QueryParam("name") String name, @QueryParam("password") String password){
		User user = new User(name, "", "", "", "", password);
		return userServices.credentialsCheck(user)==true ? "true" : "false";
	}
	
	/*the method to get all registered user*/
	@GET
	@Path("getRegistereduser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getRegistereduser(){
		try{
			return userServices.getRegistereduser();
		}catch(Exception exception){
			exception.printStackTrace();
			return null;
		}		
	}
	/*the methoddelete the */
	@GET
	@Path("deleteUser")
	public String deleteUser(@QueryParam("email")String email){		
		try{
			if(userServices.deleteuser(email))
				return "success";
		}catch(Exception exception){
			exception.printStackTrace();
			return null;
		}
		return email;		
	}
	
	/*update the user data */
	@GET
	@Path("updateUser")
	public String updateUser(@QueryParam("email")String email, @QueryParam("name")String name,@QueryParam("mobile")String mobile){		
		try{
			if(userServices.updateUser(new User(name, email, mobile, "", "", "")));
				return "success";
		}catch(Exception exception){
			exception.printStackTrace();
			return null;
		}
		return email;		
	}
}
