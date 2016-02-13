package fc.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import fc.dao.UserDao;
import fcs.bean.User;

public class UserServices {
	UserDao userDao;
	
	public UserServices() {
		userDao = new UserDao();
	}
	
	public String loginAdmin(User user){
		if(userDao.authenticateUser(user)){
			user.setPassword(getRandomNumber()+"");
			if(userDao.setToken(user))
				return user.getPassword();
		}			
		return "-1";
	}
	/*This will generate the random number*/
	private int getRandomNumber(){
		System.out.println("Inside ramdom number generator");
		int randomNum;
		Random rn = new Random();
		int n = 999999999 - 1111111111 + 1;
		int i = rn.nextInt() % n;
		randomNum =  1111111111 + i;
		System.out.println("Random number generated as "+randomNum);
		return randomNum;
	}

	public boolean credentialsCheck(User user) {
		return userDao.credentialsCheck(user);
	}

	public String signUpUser(User user) {
		return userDao.signupUser(user);
	}

	public List<User> getRegistereduser() throws Exception {
		return userDao.getRegisteredUser();
	}

	public boolean deleteuser(String email) {
		return userDao.deleteUser(email);		
	}

	public boolean updateUser(User user) {
		return userDao.updateUser(user);	
	}

	public boolean logout() {
		return userDao.logout();
	}

	public String foodCollectionRequest(User user) throws SQLException {
		return userDao.foodCollectionRequest(user,"REQ"+getRandomNumber());		
	}
	
}
