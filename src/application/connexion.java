package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class connexion {
	public static Connection connectDb() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/book", "root", ""); 
			return connect;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}

}
