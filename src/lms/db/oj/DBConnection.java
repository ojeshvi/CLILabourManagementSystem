package lms.db.oj;

import java.sql.*;

public class DBConnection {
	Connection con=null;
	Connection startConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/lms", "root",
					"OJ12345");
			 //System.out.println("Connection Established");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return con;
	}
	void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("exception raised");
		}
	}
	
}
