package appSpecs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entities.Book;
import entities.Transaction;
import entities.User;

public class DBServices implements IDBOperations{
	
	private Connection conn;
	
	public DBServices(){
		conn = DBConnection.getConnection().getConn();
	}
	
	public void closeConnection(){
		try{
			conn.close();
			}
		catch(SQLException ex){
			System.out.println("Connection could not be closed!");
			ex.printStackTrace();
		}
	}

//	@Override
//	public ArrayList<Book> selectAllBooks(){
//		try{
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM book");
//			ArrayList <Book> books = new ArrayList<Book>();
//			while(rs.next()){
//				Book fetchedBook = new Book(rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("state"), rs.getInt("id"));
//				books.add(fetchedBook);
//			}
//			stmt.close();
//			return books;
//		}catch(Exception e){
//			System.err.println(e);
//		}
//		return null;
//	}
//	public ArrayList<Book> selectAllBooksAvailable(){
//		try{
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE id NOT IN (SELECT bookid FROM transaction WHERE dateofreturn is null)");
//			ArrayList <Book> books = new ArrayList<Book>();
//			while(rs.next()){
//				Book fetchedBook = new Book(rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("state"), rs.getInt("id"));
//				books.add(fetchedBook);
//			}
//			stmt.close();
//			return books;
//		}catch(Exception e){
//			System.err.println(e);
//		}
//		return null;
//	}
//	@Override
//	public Book selectBookById(int id){
//		try{
//			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM book WHERE id =?");
//			stmt.setInt(1, id);
//			ResultSet rs = stmt.executeQuery();
//			if(rs.next()){
//				Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("state"), rs.getInt("id"));
//				rs.close();
//				stmt.close();
//				return book;
//			}
//			else{
//				rs.close();
//				stmt.close();
//			}
//			
//		}catch(Exception e){
//			System.err.println(e);
//		}
//		return null;
//	}
	
//	public void deleteBookById(int id){
//		try{
//			PreparedStatement stmt = conn.prepareStatement("DELETE FROM book WHERE id =?");
//			stmt.setInt(1, id);
//			stmt.execute();
//			stmt.close();
//		}catch(Exception e){
//			System.err.println(e);
//		}
//	}
	@Override
	public ArrayList<User> selectUser(){
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM libuser");
			ArrayList <User> users = new ArrayList<User>();
			while(rs.next()){
				User fetchedUser = new User(rs.getString("name"), rs.getString("ssn"), rs.getString("address"), rs.getInt("id"), rs.getBoolean("isadmin"));
				users.add(fetchedUser);
			}
			stmt.close();
			rs.close();
			return users;
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	
	public void deleteUserById(int id){
		try{
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM libuser WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();
		}catch(Exception e){
			System.err.println(e);
		}
	}
	public void deleteTransById(int id){
		try{
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM transaction WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void updateUserById(int id, String name, String ssn, String address, Boolean isAdmin){
		try{
			PreparedStatement stmt = conn.prepareStatement("UPDATE libuser SET	 name = ?,"
				+ "																 ssn = ?,"
				+ "																 address = ?,"
				+ "																 isadmin = ?"
				+ "											 WHERE id = ?");
			
			stmt.setString(1, name);
			stmt.setString(2, ssn);
			stmt.setString(3, address);
			stmt.setBoolean(4, isAdmin);		
			stmt.setInt(5, id);
			stmt.execute();
			
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void updateBookById(int id, String title, String author, String isbn, String state){
		try{
			PreparedStatement stmt = conn.prepareStatement("UPDATE book SET	 	 title = ?,"
				+ "																 author = ?,"
				+ "																 isbn = ?,"
				+ "																 state = ?"
				+ "											 WHERE id = ?");
			
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setString(3, isbn);
			stmt.setString(4, state);		
			stmt.setInt(5, id);
			stmt.execute();
			
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void insertBook(String title, String author, String isbn, String state){
		try{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO book (title, author, isbn, state) "
					+ "										VALUES(?,?,?,?)");
			
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setString(3, isbn);
			stmt.setString(4, state);		
			stmt.execute();
			
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void insertUser(String name, String ssn, String address, Boolean isAdmin){
		try{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO libuser (name, ssn, address, isadmin) "
					+ "										VALUES(?,?,?,?)");
			
			stmt.setString(1, name);
			stmt.setString(2, ssn);
			stmt.setString(3, address);
			stmt.setBoolean(4, isAdmin);		
			stmt.execute();
			
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public ArrayList<User> selectUserById(int id){
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM libuser WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			ArrayList <User> users = new ArrayList<User>();
			while(rs.next()){
				User fetchedUser = new User(rs.getString("name"), rs.getString("ssn"), rs.getString("address"), rs.getInt("id"), rs.getBoolean("isadmin"));
				users.add(fetchedUser);
			}
			stmt.close();
			rs.close();
			return users;
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	
	public ArrayList<User> selectUserByName(String name){
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM libuser WHERE name = ?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			ArrayList <User> users = new ArrayList<User>();
			while(rs.next()){
				User fetchedUser = new User(rs.getString("name"), rs.getString("ssn"), rs.getString("address"), rs.getInt("id"), rs.getBoolean("isadmin"));
				users.add(fetchedUser);
			}
			stmt.close();
			return users;
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	
	public ArrayList<Transaction> selectActiveBorrowedByUser(int id){
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT t.id, t.userid, t.bookid, t.dateofborrow, t.expecteddateofreturn, t.dateofreturn, u.name AS username, b.title AS booktitle"
					+ "							 	FROM transaction t, libuser u, book b "
					+ "								WHERE 	u.id = t.userid AND"
					+ "										b.id = t.bookid AND"
					+ "										dateofreturn is null AND" 
					+ "										u.id = ?");
						
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			ArrayList <Transaction> trans = new ArrayList<Transaction>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
			while(rs.next()){
//				Transaction fetchedTrans = new Transaction(	rs.getInt("userid"),
//															rs.getInt("bookid"),
//															LocalDate.parse(rs.getString("dateofborrow"), format),
//															LocalDate.parse(rs.getString("expecteddateofreturn"), format),
//															rs.getString("dateofreturn") != null? LocalDate.parse(rs.getString("dateofreturn"), format) : null,
//															rs.getInt("id"),
//															rs.getString("booktitle"),
//															rs.getString("username"));
//				trans.add(fetchedTrans);
			}
			stmt.close();
			return trans;
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	
	public ArrayList<Transaction> selectAllBorrowedByUser(int id){
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT t.id, t.userid, t.bookid, t.dateofborrow, t.expecteddateofreturn, t.dateofreturn, u.name AS username, b.title AS booktitle"
					+ "							 	FROM transaction t, libuser u, book b "
					+ "								WHERE 	u.id = t.userid AND"
					+ "										b.id = t.bookid AND" 
					+ "										u.id = ?");
						
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			ArrayList <Transaction> trans = new ArrayList<Transaction>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
			while(rs.next()){
//				Transaction fetchedTrans = new Transaction(	rs.getInt("userid"),
//															rs.getInt("bookid"),
//															LocalDate.parse(rs.getString("dateofborrow"), format),
//															LocalDate.parse(rs.getString("expecteddateofreturn"), format),
//															rs.getString("dateofreturn") != null? LocalDate.parse(rs.getString("dateofreturn"), format) : null,
//															rs.getInt("id"),
//															rs.getString("booktitle"),
//															rs.getString("username"));
//				trans.add(fetchedTrans);
			}
			stmt.close();
			return trans;
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	
	
	
	@Override
	public ArrayList<Transaction> selectTrans(){
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT t.id, t.userid, t.bookid, t.dateofborrow, t.expecteddateofreturn, t.dateofreturn, u.name AS username, b.title AS booktitle"
					+ "							 	FROM transaction t, libuser u, book b "
					+ "								WHERE 	u.id = t.userid AND"
					+ "										b.id = t.bookid");
			ArrayList <Transaction> trans = new ArrayList<Transaction>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
			while(rs.next()){
//				Transaction fetchedTrans = new Transaction(	rs.getInt("userid"),
//															rs.getInt("bookid"),
//															LocalDate.parse(rs.getString("dateofborrow"), format),
//															LocalDate.parse(rs.getString("expecteddateofreturn"), format),
//															rs.getString("dateofreturn") != null? LocalDate.parse(rs.getString("dateofreturn"), format) : null,
//															rs.getInt("id"),
//															rs.getString("booktitle"),
//															rs.getString("username"));
//				trans.add(fetchedTrans);
			}
			stmt.close();
			return trans;
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	@Override
	public ArrayList<Transaction> selectTransActive(){
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT t.id, t.userid, t.bookid, t.dateofborrow, t.expecteddateofreturn, t.dateofreturn, u.name AS username, b.title AS booktitle"
					+ "							 	FROM transaction t, libuser u, book b "
					+ "								WHERE 	u.id = t.userid AND"
					+ "										b.id = t.bookid AND"
					+ "										t.dateofreturn is null");
			ArrayList <Transaction> trans = new ArrayList<Transaction>();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
			while(rs.next()){
//				Transaction fetchedTrans = new Transaction(	rs.getInt("userid"),
//															rs.getInt("bookid"),
//															LocalDate.parse(rs.getString("dateofborrow"), format),
//															LocalDate.parse(rs.getString("expecteddateofreturn"), format),
//															((rs.getString("dateofreturn") == null)? null : (LocalDate.parse(rs.getString("dateofreturn"), format))),
//															rs.getInt("id"),
//															rs.getString("booktitle"),
//															rs.getString("username"));
//				trans.add(fetchedTrans);
			}
			stmt.close();
			return trans;
		}catch(Exception e){
			System.err.println(e);
		}
		
		return null;
	}
	@Override
	public void insertTrans(int userid, int bookid, LocalDate dateOfBorrow, LocalDate expectedDateOfReturn){
		try{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO transaction (userid, bookid, dateofborrow, expecteddateofreturn) "
					+ "						VALUES(?, ?, ? , ?)");
			stmt.setInt(1, userid);
			stmt.setInt(2, bookid);
			stmt.setDate(3, java.sql.Date.valueOf(dateOfBorrow));
			stmt.setDate(4, java.sql.Date.valueOf(expectedDateOfReturn));

			
			stmt.execute();
			stmt.close();
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void receiveBook(int id, LocalDate dateOfReturn){
		try{
			PreparedStatement stmt = conn.prepareStatement("UPDATE transaction SET dateofreturn = ? WHERE id =?");
			stmt.setDate(1, java.sql.Date.valueOf(dateOfReturn));
			stmt.setInt(2, id);
			stmt.execute();
			stmt.close();
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public void updateTransById(int id, int userid, int bookid, LocalDate dateOfBorrow, LocalDate expectedDateOfReturn, LocalDate dateOfReturn){
		try{
			PreparedStatement stmt = conn.prepareStatement("UPDATE transaction SET"
					+ "																	userid = ?,"
					+ "																	bookid = ?,"
					+ "																	dateofborrow = ?,"
					+ "																	expecteddateofreturn = ?,"
					+ "																	dateofreturn = ?,"
					+ "										 WHERE id =?");
			
			stmt.setInt(1, userid);
			stmt.setInt(2, bookid);
			stmt.setDate(1, java.sql.Date.valueOf(dateOfBorrow));
			stmt.setDate(1, java.sql.Date.valueOf(expectedDateOfReturn));
			stmt.setDate(1, java.sql.Date.valueOf(dateOfReturn));
			stmt.execute();
			stmt.close();
		}catch(Exception e){
			System.err.println(e);
		}
	}
	
	public Transaction selectTransById(int id){
		try{
			PreparedStatement stmt = conn.prepareStatement("SELECT t.id, t.userid, t.bookid, t.dateofborrow, t.expecteddateofreturn, t.dateofreturn, u.name AS username, b.title AS booktitle"
					+ "							 	FROM transaction t, libuser u, book b "
					+ "								WHERE 	u.id = t.userid AND"
					+ "										b.id = t.bookid AND"
					+ "										t.id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-MM-dd");
			if(rs.next()){
//				Transaction fetchedTrans = new Transaction(	rs.getInt("userid"),
//							rs.getInt("bookid"),
//							LocalDate.parse(rs.getString("dateofborrow"), format),
//							LocalDate.parse(rs.getString("expecteddateofreturn"), format),
//							rs.getString("dateofreturn") != null? LocalDate.parse(rs.getString("dateofreturn"), format) : null,
//							rs.getInt("id"),
//							rs.getString("booktitle"),
//							rs.getString("username"));
//				rs.close();
//				stmt.close();
//				return fetchedTrans;
			}
			else{
				rs.close();
				stmt.close();
				return null;
				}	
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	
}

