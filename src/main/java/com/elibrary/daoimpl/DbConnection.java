package com.elibrary.daoimpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConnection {
	private static final String JPA_UNIT_NAME = "ELibrary";
	private static DbConnection connection = null;
	private EntityManagerFactory emf;
//	URI dbUri = null;
//	String username = dbUri.getUserInfo().split(":")[0];
//	String password = dbUri.getUserInfo().split(":")[1];
//	String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
//            + dbUri.getPort() + dbUri.getPath()
//            + "?sslmode=require";
//	
//    private static Connection getConnection() throws URISyntaxException, SQLException {
//        URI dbUri = null;
//        if(System.getenv("DATABASE_URL") != null) {
//            dbUri = new URI(System.getenv("DATABASE_URL"));
//        }else {
//            String DATABASE_URL = "postgres://ubuntu:ubuntu@localhost:5432/userdb";
//            dbUri = new URI(DATABASE_URL);
//        }
//
//		String username = dbUri.getUserInfo().split(":")[0];
//		String password = dbUri.getUserInfo().split(":")[1];
//		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
//                + dbUri.getPort() + dbUri.getPath()
//                + "?sslmode=require";
//        /*Connection connection = DriverManager.getConnection(
//                "jdbc:postgresql://localhost:5432/userdb?sslmode=require",
//                "ubuntu",
//                "ubuntu");*/
//		return DriverManager.getConnection(dbUrl, username, password);
//	}
//	
	private DbConnection() {
		init();
	}
	
	private void init() {
		
		String databaseUrl = System.getenv("DATABASE_URL");
		StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
		String dbVendor = st.nextToken(); //if DATABASE_URL is set
		String userName = st.nextToken();
		String password = st.nextToken();
		String host = st.nextToken();
		String port = st.nextToken();
		String databaseName = st.nextToken();
		String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host, port, databaseName);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", databaseUrl );
		properties.put("javax.persistence.jdbc.user", userName );
		properties.put("javax.persistence.jdbc.password", password );
		properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		emf = Persistence.createEntityManagerFactory("default", properties);
//		if(emf == null || !emf.isOpen()) {
//			emf = Persistence.createEntityManagerFactory(JPA_UNIT_NAME);
//		}
	}
	
	public static DbConnection getInstance() {
		if(connection == null)
			connection = new DbConnection();
		
		return connection;
	}
	
	public EntityManager getEntityManager() {
		init();
		return emf.createEntityManager();
	}
	
	public void close() {
		emf.close();
	}
	
}
