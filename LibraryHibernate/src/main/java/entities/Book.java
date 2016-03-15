package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name="book")
public class Book {
	@Transient
	public static int nextId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String title;
	private String author;
	private String isbn;
	private String state;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="book")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Transaction> transaction = new ArrayList<Transaction>();
	
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

	public Book(){}
	
	public Book(String title, String author, String isbn, String state){
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.state = state;
		//this.id = id;
		//nextId = id + 1;
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
	public Collection<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
}
