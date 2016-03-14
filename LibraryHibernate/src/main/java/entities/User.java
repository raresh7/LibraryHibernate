package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(	name="libuser", uniqueConstraints = @UniqueConstraint(columnNames={"name"}))
public class User {
	@Transient
	public static int nextId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String ssn;
	private String address;
	private Boolean isAdmin;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Transaction> transaction = new ArrayList<Transaction>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User(){}
	
	public User(String name, String ssn, String address, int id, Boolean isAdmin){
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.id = id;
		this.isAdmin = isAdmin;
		nextId = id + 1;
	}
	public User(String name, String ssn, String address, Boolean isAdmin){
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.isAdmin = isAdmin;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public static User getUser(String name){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
		Session session = sessionFactory.openSession();
		User user = (User) session.createCriteria( User.class ).add( Restrictions.eq("name", name) ).uniqueResult();
		session.close();
		return user;

		}
	
	public static List<User> getAll(){
		List <User> users = new ArrayList<User>();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
		Session session = sessionFactory.openSession();
		Query qry = session.createQuery("FROM User");
		users = (List<User>)qry.list();
		session.close();
		return users;

		}

	public static User getUser(int id){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
		Session session = sessionFactory.openSession();
		User user = (User) session.createCriteria( User.class ).add( Restrictions.eq("id", id)).uniqueResult();
		session.close();
		return user;

		}
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
}
