package com.bai4.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private final String serverName = "DESKTOP-DJ03I8E\\HUYRO";
	private final String dbName = "User_LTWeb";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "31121124";

	public Connection getConnection() throws SQLException {
		String url;

		if (instance == null || instance.trim().isEmpty()) {
		    url = "jdbc:sqlserver://" + serverName + ":" + portNumber
		        + ";databaseName=" + dbName
		        + ";user=" + userID
		        + ";password=" + password
		        + ";encrypt=true;trustServerCertificate=true";
		} else {
		    url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber
		        + ";databaseName=" + dbName
		        + ";user=" + userID
		        + ";password=" + password
		        + ";encrypt=true;trustServerCertificate=true";
		}


		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("SQL Server JDBC Driver not found.", e);
		}

		return DriverManager.getConnection(url);
	}

	// test connection
	public static void main(String[] args) {
		try (Connection c = new DBConnect().getConnection()) {
			System.out.println("Kết nối thành công: " + (c != null && !c.isClosed()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}