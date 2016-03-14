package appSpecs;

import java.time.LocalDate;
import java.util.ArrayList;

import entities.Book;
import entities.Transaction;
import entities.User;

public interface IDBOperations {
//	public ArrayList<Book> selectAllBooks();
//	public Book selectBookById(int id);
	public ArrayList<User> selectUser();
	public ArrayList<Transaction> selectTrans();
	public ArrayList<Transaction> selectTransActive();
	public void insertTrans(int userid, int bookid, LocalDate dateOfBorrow, LocalDate expectedDateOfReturn);
}
