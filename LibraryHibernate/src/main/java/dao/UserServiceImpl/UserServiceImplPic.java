package dao.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import dao.UserService;
import entities.User;

public class UserServiceImplPic implements UserService{

    protected UserServiceImplPic(){System.out.println("PIC constructor");};
    
    @Override
    public List<User> getAll() {
        List <User> users = new ArrayList<User>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        Query qry = session.createQuery("FROM User");
        users = (List<User>)qry.list();
        session.close();
        System.out.println("PIC getAll()");
        return users;
    }

    @Override
    public User getUser(int id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        User user = (User) session.createCriteria( User.class ).add( Restrictions.eq("id", id)).uniqueResult();
        session.close();
        System.out.println("PIC getUser by id");
        return user;
    }

    @Override
    public User getUser(String name) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        User user = (User) session.createCriteria( User.class ).add( Restrictions.eq("name", name) ).uniqueResult();
        session.close();
        System.out.println("PIC getUser by name");
        return user;
    }
    
}
