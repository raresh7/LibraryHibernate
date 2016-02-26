package appSpecs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	public Connection connection;
	public static DBConnection db;
	
	private DBConnection(){
		String URL = "jdbc:postgresql://localhost:5432/postgres";
		String USER = "postgres";
		String PASS = "admin";
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USER, PASS);
		}
		catch(ClassNotFoundException ex){
			System.out.println("Driver not found!");
			ex.printStackTrace();
		}
		catch(SQLException e){
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	public synchronized static DBConnection getConnection(){
		if(db==null){
			db = new DBConnection();
		}
		if(db != null){
			System.out.println("database connection done!");
		}
		else{
			System.out.println("connection failed!");
		}
		return db;
	}
	public static void setDatabase(){
		db = null;
	}
	
	public synchronized Connection getConn(){
		try{
			if(connection == null || connection.isClosed())
				new DBConnection();
		}
		catch(SQLException e){
			System.out.println("connection could not be done");
			e.printStackTrace();
		}
		return connection;
		
	}
}
