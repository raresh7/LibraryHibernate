package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import appSpecs.DBServices;
import entities.Book;
import entities.Transaction;
import entities.User;

/**
 * Servlet implementation class AddTrans
 */
@WebServlet(urlPatterns={"/addtrans"}, name="AddTransServlet")
public class AddTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTrans() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user;
		user = (User)session.getAttribute("loggedUser");
		if(user.getIsAdmin()){
			List <Book> avBooks = new ArrayList<Book>();
			avBooks = Book.getAvailableBooks();
			request.setAttribute("books", avBooks);
			request.setAttribute("users", User.getAll());
			request.getRequestDispatcher("newtrans.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session hsession = sessionFactory.openSession();
		hsession.beginTransaction();
		Transaction trans = new Transaction(User.getUser(Integer.parseInt(request.getParameter("user"))),
											Book.getBook(Integer.parseInt(request.getParameter("book"))),
											LocalDate.parse(request.getParameter("dateOfBorrow"), format),
											LocalDate.parse(request.getParameter("expectedDateOfReturn"), format));
		hsession.save(trans);
		hsession.getTransaction().commit();
		hsession.close();
		sessionFactory.close();	
		response.sendRedirect("addtrans");
				
	}

}
