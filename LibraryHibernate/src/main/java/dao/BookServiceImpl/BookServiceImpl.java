package dao.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import dao.BookService;
import entities.Book;

public class BookServiceImpl implements BookService{
    
//    protected BookServiceImpl(){};
    
    public List<Book> getAvailableBooks(){
        List <Book> books = new ArrayList<Book>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        Query qry = session.createQuery("FROM Book b"
                + "                         WHERE b.id NOT IN (SELECT book FROM Transaction WHERE dateofreturn is null)");
        books = (List<Book>)qry.list();
        session.close();
        return books;
    }
    
    public Book getBook(int id){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        Book book = (Book) session.createCriteria( Book.class ).add( Restrictions.eq("id", id)).uniqueResult();
        session.close();
        return book;

        }
    public List<Book> getAll(){
        List <Book> books = new ArrayList<Book>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        Query qry = session.createQuery("FROM Book");
        books = (List<Book>)qry.list();
        session.close();
        return books;

        }
    
}
