package DataClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) {
		Statement s = DataBaseConnection.getStatment();
		try {
			
			ResultSet r = s.executeQuery("Select * from levels;");
			//r.next();
			ArrayList<String> arrayList = new ArrayList<>();
	        arrayList.add("One");
	        arrayList.add("Two");
	        arrayList.add("Three");
				System.out.println(arrayList.toArray());
				for(Object sy : arrayList.toArray()) {
					System.out.println(sy);
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
