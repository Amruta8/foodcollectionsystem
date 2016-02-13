package fc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String sqlQueryCollector = "insert into collector_availability(user_email,status) value(\""+user.getEmail()+"\",\"Ideal\")";
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

	public String foodCollectionRequest(User user, String requestNumber) throws SQLException {
		System.out.println("into UserDao.foodCollectionRequest");
		try {
			String sqlQuery = "insert into collection_request(req_name,req_location,req_address,req_contact,req_quantity,email,req_number) value(\""+user.getName()+"\",\""+user.getLocation()+"\",\""+user.getAddress()+"\",\""+user.getMobile()+"\",\""+user.getRequestedQuantity()+"\",\""+user.getEmail()+"\",\""+requestNumber+"\")";
			System.out.println("final insert query"+sqlQuery);
			PreparedStatement pstmt= connection.prepareStatement(sqlQuery);
			pstmt.executeUpdate();
			return requestNumber;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
