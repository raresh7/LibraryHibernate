package dao.TransactionServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import dao.TransactionService;
import entities.Transaction;

public class TransactionServiceImpl implements TransactionService {

//    protected TransactionServiceImpl(){}
    
    @Override
    public List<Transaction> returnActiveTrans(List<Transaction> trans) {
        for(int i=0;i<trans.size();i++){
            if(trans.get(i).getDateOfReturn() != null){
                trans.remove(i);
                i--;
            }
        }
        return trans;
    }

    @Override
    public void returnBooks(List<Integer> ids, LocalDate dateOfReturn) {
        List <Transaction> trans = new ArrayList<Transaction>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session hsession = sessionFactory.openSession();
        hsession.beginTransaction();
        trans = (List<Transaction>)hsession.createCriteria( Transaction.class ).add( Restrictions.in("id", ids)).list();
        for(int i = 0; i<trans.size();i++){
            trans.get(i).setDateOfReturn(dateOfReturn);
            hsession.save(trans.get(i));
        }
        
        hsession.getTransaction().commit();
        hsession.close();
        sessionFactory.close(); 
        
    }

    @Override
    public List<Transaction> getAllActive() {
        List <Transaction> trans = new ArrayList<Transaction>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        Query qry = session.createQuery("FROM Transaction"
                + "                         WHERE dateofreturn is null");
        trans = (List<Transaction>)qry.list();
        session.close();
        return trans;
    }

    @Override
    public List<Transaction> getAll() {
        List <Transaction> trans = new ArrayList<Transaction>();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        Query qry = session.createQuery("FROM Transaction");
        trans = (List<Transaction>)qry.list();
        session.close();
        return trans;
    }

    @Override
    public Transaction deleteTransaction(int id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Transaction trans = (Transaction) session.createCriteria( Transaction.class ).add( Restrictions.eq("id", id)).uniqueResult();
        session.delete(trans);
        session.getTransaction().commit();
        session.close();
        return trans;
    }

    @Override
    public Transaction getTransaction(int id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
        Session session = sessionFactory.openSession();
        Transaction trans = (Transaction) session.createCriteria( Transaction.class ).add( Restrictions.eq("id", id)).uniqueResult();
        session.close();
        return trans;
    }

}
