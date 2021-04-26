package com.sb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/simpleboard?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String id = "";
	private static String password = "";

	private static DBUtil util = new DBUtil();
	public static DBUtil getUtil() {
		return util;
	}
	
	private DBUtil(){
		try(BufferedReader br=new BufferedReader(new FileReader(new File("./secreat.txt")))) {	
			Class.forName(DRIVER);
			id = br.readLine();
			password=br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnect() throws SQLException{
		return DriverManager.getConnection(URL, id, password);
	}

	public void close(AutoCloseable... closeables) {
		for (AutoCloseable ac : closeables) {
			if (ac != null)
				try {
					ac.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

}
