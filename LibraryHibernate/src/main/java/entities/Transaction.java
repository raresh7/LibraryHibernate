package entities;

import java.time.LocalDate;

public class Transaction {
	public static int nextId;
	private int id;
	private int userid;
	private int bookid;
	private String username;
	private String booktitle;
	private LocalDate dateOfBorrow;
	private LocalDate expectedDateOfReturn;
	private LocalDate dateOfReturn;
	

	public LocalDate  getDateOfBorrow() {
		return dateOfBorrow;
	}
	public void setDateOfBorrow(LocalDate  dateOfBorrow) {
		this.dateOfBorrow = dateOfBorrow;
	}
	public LocalDate  getExpectedDateOfReturn() {
		return expectedDateOfReturn;
	}
	public void setExpectedDateOfReturn(LocalDate  expectedDateofReturn) {
		this.expectedDateOfReturn = expectedDateofReturn;
	}
	public LocalDate  getDateOfReturn() {
		return dateOfReturn;
	}
	public void setDateOfReturn(LocalDate  dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Transaction(int user, int book, LocalDate dateOfBorrow, LocalDate expectedDateOfReturn, int id, String booktitle, String username){
		this.userid = user;
		this.bookid = book;
		this.dateOfBorrow = dateOfBorrow;
		this.expectedDateOfReturn = expectedDateOfReturn;
		this.booktitle = booktitle;
		this.username = username;
		this.id = id;
		nextId = id+1;
	}
	public Transaction(int user, int book, LocalDate dateOfBorrow, LocalDate expectedDateOfReturn, LocalDate dateofreturn, int id, String booktitle, String username){
		this.userid = user;
		this.bookid = book;
		this.dateOfBorrow = dateOfBorrow;
		this.expectedDateOfReturn = expectedDateOfReturn;
		this.dateOfReturn = dateofreturn;
		this.booktitle = booktitle;
		this.username = username;
		this.id = id;
		nextId = id+1;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
}
