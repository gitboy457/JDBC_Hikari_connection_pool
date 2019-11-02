package com.ace.jdbcDemo.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDBConfig {

	private static final Properties prop;

	static {
		// load properties file
		prop = new Properties();
		try {
			prop.load(CustomerRepositoryImp.class.getClassLoader().getResourceAsStream("customer.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// loading driver class
		try {
			Class.forName(prop.getProperty("db.mysql.driver.class.name"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static HikariConfig cfg = getHikariConfig();
	private static HikariDataSource ds = new HikariDataSource(cfg);
	private Connection con = null;

	public HikariDBConfig() {
		super();
	}

	public Connection getNewDBConnection() {

		try {
				con = ds.getConnection();
				con.setAutoCommit(false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	private static HikariConfig getHikariConfig() {
		HikariConfig hc = new HikariConfig();
		// set db configuration
		hc.setDriverClassName(prop.getProperty("db.mysql.driver.class.name"));
		hc.setJdbcUrl(prop.getProperty("db.mySql.jdbcUrl"));
		hc.setUsername(prop.getProperty("db.mySql.user"));
		hc.setPassword(prop.getProperty("db.mySql.password"));

		// set pool configuration
		hc.setMaximumPoolSize(Integer.parseInt(prop.getProperty("db.mysql.connectionPool.size")));
		hc.setAutoCommit(Boolean.parseBoolean(prop.getProperty("db.mysql.connectionPool.autoCommit")));
		return hc;

	}
}
