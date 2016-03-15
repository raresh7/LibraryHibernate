package hibernate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Book;
import entities.Transaction;
import entities.User;

public class TesterClass {

	public static void main(String[] args) {

		Book book = new Book("first book", "gimi riter", "1234", "good");
		Book book2 = new Book("first book", "gimi riter", "1234", "bad");
		User user = new User("username32", "`1134", "asdasgd", true);
		User user2 = new User("username22", "`1134", "asdasgd", true);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
//		Transaction trans = new Transaction(user, book, LocalDate.parse("22.02.2002", format), LocalDate.parse("22.03.2002", format));
//		user.getTransaction().add(trans);
//		book.getTransaction().add(trans);
//		session.save(trans);
//		Transaction trans2 = new Transaction(user2, book, LocalDate.parse("22.02.2002", format), LocalDate.parse("22.03.2002", format));
//		user2.getTransaction().add(trans2);
//		book.getTransaction().add(trans2);
//		session.save(trans2);
//		
//		session.save(trans);
//		session.save(book);
//		session.save(book2);
//		session.save(user);
//		session.save(user2);
		
		
//		System.out.println(Book.getAvailableBooks().size());
		
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}

}
