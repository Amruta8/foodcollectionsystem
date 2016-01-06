
package fc.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class ConnectionProvider {
	private static Connection con=null;
	public static Connection getConnection() throws  Exception{	
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("/foodorderingsystemresources.properties")));
			if(con==null){
				Class.forName(properties.getProperty("driver"));
				con=DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			}
		} catch (IOException e) {
			//e.printStackTrace();
			if(con==null){
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FCS_STAGE", "root", "root");
			}
		}	
		return con;
	} 
}
