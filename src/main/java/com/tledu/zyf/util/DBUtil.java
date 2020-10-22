package com.tledu.zyf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		// properties.load(new FileReader("./src/jdbc.properties"));

		// DBUtil.class 运行时类
		// getClassLoader : 获取类加载器
		// getResourceAsStream : 把资源转换为流
		properties.load(DBUtil.class.getClassLoader().getResourceAsStream(
				"jdbc.properties"));

		System.out.println(properties.getProperty("driver"));
	}

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Properties properties = PropertiesUtil.getProperties();

		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, username,
				password);
		return connection;
	}

	public static void close(AutoCloseable autoCloseable) {
		try {
			if (autoCloseable != null) {
				autoCloseable.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
