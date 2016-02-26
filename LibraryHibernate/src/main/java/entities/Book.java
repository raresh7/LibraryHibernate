package entities;

public class Book {
	public static int nextId;
	private int id; 
	private String title;
	private String author;
	private String isbn;
	private String state;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	
	public Book(String title, String author, String isbn, String state, int id){
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.state = state;
		this.id = id;
		nextId = id + 1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
