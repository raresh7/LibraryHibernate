package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import appSpecs.DateConverter;

@Entity
@Table(name="transaction")
public class Transaction {
	@Transient
	public static int nextId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = User.class)
	private User user;
	@ManyToOne(targetEntity = Book.class)
	private Book book;
	
	@Column(columnDefinition="DATE")
	@Convert(converter = DateConverter.class)
	private LocalDate dateOfBorrow;
	@Column(columnDefinition="DATE")
	@Convert(converter = DateConverter.class)
	private LocalDate expectedDateOfReturn;
	@Column(columnDefinition="DATE")
	@Convert(converter = DateConverter.class)
	private LocalDate dateOfReturn;
	

	public Transaction(){}
	
	public Date getDateOfBorrow() {
		return Date.valueOf(dateOfBorrow);
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
	
	public Transaction(User user, Book book, LocalDate dateOfBorrow, LocalDate expectedDateOfReturn){
		this.user = user;
		this.book = book;
		this.dateOfBorrow = dateOfBorrow;
		this.expectedDateOfReturn = expectedDateOfReturn;


		nextId = id+1;
	}
	public Transaction(User user, Book book, LocalDate dateOfBorrow, LocalDate expectedDateOfReturn, LocalDate dateofreturn){
		this.user = user;
		this.book = book;
		this.dateOfBorrow = dateOfBorrow;
		this.expectedDateOfReturn = expectedDateOfReturn;
		this.dateOfReturn = dateofreturn;

		nextId = id+1;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	}