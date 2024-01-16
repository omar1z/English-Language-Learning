package DataClass;
import java.io.*;
import java.sql.*;
import java.util.*;
public class DataBaseConnection {
 private static Statement statement = null;
 private static  Connection connection;
 private DataBaseConnection() {
	 
 }
 public static synchronized Statement getStatment() {
	 
	 if(connection == null) {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/englishcourse";
		 	String user="root";
		 	connection  = DriverManager.getConnection(url,user,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());;
			e.printStackTrace();
		}
	 }
	
	 try {
		return connection.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
 }
}
