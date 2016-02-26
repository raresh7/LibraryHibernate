package appSpecs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entities.Book;
import entities.Transaction;
import entities.User;

public class AppSettings {
	private List<Tab> tabs;
	private List<User> users;
	private List<Book> books;
	private List<Transaction> trans;
	
	public List<Tab> getTabs() {
		return tabs;
	}
	public void setTabs(List<Tab> tabs) {
		this.tabs = tabs;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public List<Transaction> getTrans() {
		return trans;
	}
	public void setTrans(List<Transaction> trans) {
		this.trans = trans;
	}

	private List<Tab> initTabs(){
		List<Tab> tabs = new ArrayList<Tab>();
		//tabs.add(new Tab("Home", "/home", false));
		tabs.add(new Tab("User Details", "userdetails.jsp", false));
		tabs.add(new Tab("My Books", "borrowed?all=true", false));
		tabs.add(new Tab("Not Returned", "borrowed", false));
		
		tabs.add(new Tab("All Users", "userlist", true));
		tabs.add(new Tab("All Books", "booklist", true));
		tabs.add(new Tab("Lend Book", "lentbooks", true));
		tabs.add(new Tab("Receive Books", "returnbooks", true));
		
		return tabs;
	}
	
	private List<User> initUsers(){
		List<User> users = new ArrayList<User>();
		int i;
		for(i = 0; i<10; i++){
			users.add(new User("user"+i,"12345"+i,"street X, no." + i, i+1, false));
		}
		
		users.add(new User("admin","adminSSN","library", i+1, true));
		
		return users;
	}
	
	private List<Book> initBooks(){
		List<Book> books = new ArrayList<Book>();
		for(int i = 0; i<10; i++)
			books.add(new Book("Book of "+(100-i),"Author - "+i,"abcdef-4" + i, "good", i+1));
		return books;
	}
	
	private List<Transaction> initTrans(){
		List<Book> books = getBooks();
		List<User> users = getUsers();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		
		List<Transaction> trans = new ArrayList<Transaction>();
		for(int i=0;i<8;i++){
			//trans.add(new Transaction(users.get(8), books.get(i), LocalDate.parse("11.11.2015", format), LocalDate.parse("11.12.2015", format), i+1));
		}	
		return trans;
	}
	public AppSettings(){
//		this.books = initBooks();
		this.tabs = initTabs();
//		this.users = initUsers();
//		this.trans = initTrans();	
	}
	
	public User getUserById(int id){
		int i = 0;
		while(i<users.size()){
			if(users.get(i).getId() == id)
				return users.get(i);
			i++;
		}
		return null;
	}
	
	public User getUserByName(String name){
		int i = 0;
		while(i<users.size()){
			if(users.get(i).getName().equals(name))
				return users.get(i);
			i++;
		}
		return null;
	}
	
	public Book getBookById(int id){
		int i = 0;
		while(i<books.size()){
			if(books.get(i).getId() == id)
				return books.get(i);
			i++;
		}
		return null;
	}
	
	public Transaction getTransById(int id){
		int i = 0;
		while(i<trans.size()){
			if(trans.get(i).getId() == id)
				return trans.get(i);
			i++;
		}
		return null;
	}
	
	
}
