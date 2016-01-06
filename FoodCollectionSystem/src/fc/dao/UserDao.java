package fc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
