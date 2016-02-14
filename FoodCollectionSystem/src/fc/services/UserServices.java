package fc.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.constant.FCSConstants;

import fc.dao.UserDao;
import fc.util.DistanceConverter;
import fc.util.FCSUtil;
import fcs.bean.ResourceAllocationBean;
import fcs.bean.User;

public class UserServices {
	UserDao userDao;
	private static Integer GEO_FENCE = 7; 
	
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
		List<String> collectorList = allocateCollector(user.getLocation(),user.getRequestedQuantity());
		if(collectorList!=null){
			return userDao.foodCollectionRequest(user,"REQ"+getRandomNumber(),collectorList);	
		}
		return FCSConstants.COLLECTOR_CANNOT_ASSIGNED;			
	}
	private List<String> allocateCollector(String location, String quantity) throws SQLException{
		 Map<String, String> allCollector =  userDao.getAllActiveCollectore();		 
		 Set<String> keys =  allCollector.keySet();
		 Map<String, Integer> withinGeoF = new HashMap<String, Integer>();
		 Map<String, Integer> outsideGeoF = new HashMap<String, Integer>();
		 FCSUtil fcsUtil = new FCSUtil();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String value = allCollector.get(key);
			if(DistanceConverter.distance(Double.parseDouble(location.split(",")[0]), Double.parseDouble(location.split(",")[1]), Double.parseDouble(value.split("#")[1].split(",")[0]), Double.parseDouble(value.split("#")[1].split(",")[1]), "K")>GEO_FENCE){
				outsideGeoF.put(key, Integer.parseInt(value.split("#")[0]));
			}else{
				withinGeoF.put(key, Integer.parseInt(value.split("#")[0]));
			}			
		}
		//System.out.println(DistanceConverter.distance(Integer.parseInt(location.split(",")[0]), Integer.parseInt(location.split(",")[0]), 18.5073985, 73.8076504, "K"));
		System.out.println("Outside gof : "+outsideGeoF.size() +" Indide gof : "+withinGeoF.size());
		ResourceAllocationBean allocationBean = fcsUtil.resourceAllocation(quantity, withinGeoF, outsideGeoF);		
		if(allocationBean!=null && allocationBean.getAllocatedResourcesIds().size()>0){
			System.out.println("Status : "+allocationBean.getStatus() +" Allocated collector :"+allocationBean.getAllocatedResourcesIds());
			userDao.changeCollectorStatus(allocationBean.getAllocatedResourcesIds(),FCSConstants.COLLECTION_REQUEST_ASSIGNED);
			return allocationBean.getAllocatedResourcesIds();
		}else{
			return null;
		}
		
	}

	public String foodCollectionRequestStatus(String requestId) throws SQLException {
		return userDao.foodCollectionRequestStatus(requestId);
	}
	
}
