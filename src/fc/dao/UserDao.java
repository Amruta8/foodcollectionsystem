package fc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.constant.FCSConstants;
import com.mysql.jdbc.Statement;

import fc.provider.ConnectionProvider;
import fcs.bean.User;

public class UserDao {
	static Connection connection;
	static{
		try {
			connection = ConnectionProvider.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean authenticateUser(User user){
		String sqlQuery = "select * from user where username ="+"\""+user.getName()+"\""+" and password=\""+user.getPassword()+"\" and role=\"admin\"";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			System.out.println(sqlQuery +"and execute query result is :"+preparedStatement.execute());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean setToken(User user){
		String sqlQuery = "update user set token=\""+user.getPassword()+"\" where username=\""+user.getName()+"\"";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			System.out.println(sqlQuery +"and execute query result is :"+preparedStatement.execute());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getToken(User user){

		String sqlQuery = "select * from user where username=\""+user.getName()+"\"";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			System.out.println(sqlQuery +"and execute query result is :"+preparedStatement.execute());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				return rs.getString("token");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	
	}

	public boolean credentialsCheck(User user) {
		String sqlQuery = "select * from user where username ="+"\""+user.getName()+"\""+" and token=\""+user.getPassword()+"\" and role=\"admin\"";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			System.out.println(sqlQuery +"and execute query result is :"+preparedStatement.execute());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String signupUser(User user) {
		String sqlQuery = "insert into user(username,email,mobile,password) value(\""+user.getName()+"\",\""+user.getEmail()+"\",\""+user.getMobile()+"\",\""+user.getPassword()+"\")";
		String sqlQueryCollector = "insert into collector_availability(user_email,status,qty) value(\""+user.getEmail()+"\",\"Ideal\",\""+user.getRequestedQuantity()+"\")";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sqlQuery);
			System.out.println(sqlQuery);
			preparedStatement.execute();
			preparedStatement = connection.prepareStatement(sqlQueryCollector);
			preparedStatement.execute();
			return "User registered successfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Duplicate entry";
		}
	}

	public List<User> getRegisteredUser() throws Exception{
		String sqlQuery = "select * from user u, collector_availability ca where u.email = ca.user_email and u.email not in (select  email from user where role='admin' ) ";
		List<User> userList = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			System.out.println(sqlQuery +"and execute query result is :"+preparedStatement.execute());
			ResultSet rs = preparedStatement.executeQuery();			
			while(rs.next()){
				User user = new User();
				user.setName(rs.getString("username"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
				user.setStatus(rs.getString("status"));
				userList.add(user);
			}
		} catch(SQLException exception){
			exception.printStackTrace();
			throw exception;
		}
		return userList;
	}

	public boolean deleteUser(String email) {
		try {
			String sqlQuery = "delete from user where email = \""+email+"\"";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	public boolean updateUser(User user) {		
		String sqlQuery = "update user set mobile=\""+user.getMobile()+"\",username=\""+user.getName()+"\" where email=\""+user.getEmail()+"\"";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean logout() {
		String sqlQuery = "update user set token=\"-1\" where role=\"admin\"";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public String foodCollectionRequest(User user, String requestNumber, List<String> collectorDetails) throws SQLException {
		System.out.println("into UserDao.foodCollectionRequest with user :"+user);
		try {
			String sqlReqMappingQuery = "insert into request_mapping(RequestNo,collectorIds) values('"+requestNumber+"','"+collectorDetails+"')";
			System.out.println("sqlReqMappingQuery final:"+sqlReqMappingQuery);
			PreparedStatement pstmtRMQ = connection.prepareStatement(sqlReqMappingQuery);
			pstmtRMQ.executeUpdate();
			
			String sqlQuery = "insert into collection_request(req_name,req_location,req_address,req_contact,req_quantity,email,req_number,status) value(\""+user.getName()+"\",\""+user.getLocation()+"\",\""+user.getAddress()+"\",\""+user.getMobile()+"\",\""+user.getRequestedQuantity()+"\",\""+user.getEmail()+"\",\""+requestNumber+"\",\""+FCSConstants.COLLECTION_REQUEST_ASSIGNED+"\")";
			System.out.println("final insert query"+sqlQuery);
			PreparedStatement pstmt= connection.prepareStatement(sqlQuery);
			pstmt.executeUpdate();
			return requestNumber;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	public Map<String, String> getAllActiveCollectore() {

		Map<String, String> collector = new HashMap<String, String>();
		String sqlQuery = "select * from collector_availability where status=\"collectionRequestAssigned\"";
		//select ca.user_email as user_email,ca.currentLocation as currentLocation , () from collector_availability ca, request_mapping rm, collection_request cr where status="Idle" and ca.user_email =rm.collectorIds and rm.RequestNo = cr.req_number
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			System.out.println(sqlQuery +"and execute query result is :"+preparedStatement.execute());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				collector.put(rs.getString("user_email"), rs.getString("qty")+"#"+rs.getString("currentLocation"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collector;
	}

	public boolean changeCollectorStatus(List<String> allocatedResourcesIds,String status) throws SQLException {
		String idsToUpdate="";
		for (int i = 0; i < allocatedResourcesIds.size(); i++) {
			if(i==0){
				idsToUpdate= "'"+allocatedResourcesIds.get(i)+"'";
			}else{
				idsToUpdate = idsToUpdate + ",'"+allocatedResourcesIds.get(i)+"'";
			}
		}
		String query = "update collector_availability set status='"+status+"' where user_email in ("+idsToUpdate+")";
		try {
			PreparedStatement pstmt = connection.prepareStatement(query);
			if(pstmt.executeUpdate()>0){
				return true;			
			}else{
				return false;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
			throw e;
		}
		
	}

	public String foodCollectionRequestStatus(String requestId) throws SQLException {
		String query = "select status from collection_request where req_number='"+requestId+"'";
		ResultSet rs;
		try {
			PreparedStatement preparedStatement  = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();			
		} catch (SQLException e) {			
			e.printStackTrace();
			throw e;
		}
		return rs.next() ? rs.getString("status") : "The request id is not present.Enter valid one";
	}
}
