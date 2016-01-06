package fc.services;

import java.util.Random;

import fc.dao.UserDao;
import fcs.bean.User;

public class UserServices {
	UserDao userDao;
	
	public UserServices() {
		userDao = new UserDao();
	}
	
	public String loginAdmin(User user){
		if(userDao.authenticateUser(user))
			user.setPassword(getRandomNumber()+"");
		if(userDao.setToken(user))
			return user.getPassword();
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
	
}
