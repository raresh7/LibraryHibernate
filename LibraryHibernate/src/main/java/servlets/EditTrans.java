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

import dao.BookService;
import dao.TransactionService;
import dao.UserService;
import dao.BookServiceImpl.BookServiceImpl;
import dao.TransactionServiceImpl.TransactionServiceImpl;
import dao.UserServiceImpl.UserServiceFactory;
import entities.Book;
import entities.Transaction;
import entities.User;


/**
 * Servlet implementation class EditTrans
 */
@WebServlet(urlPatterns={"/edittrans"}, name="EditTransServlet")
public class EditTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTrans() {
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
		if(user == null || user.getIsAdmin() == false)
			response.sendRedirect("index.jsp");
		else
		{
		    TransactionService transService = new TransactionServiceImpl();
		    UserService userService = UserServiceFactory.getPocUserService();
		    BookService bookService = new BookServiceImpl();
			Transaction trans = transService.getTransaction(Integer.parseInt(request.getParameter("id")));
			
			System.out.println(trans);
			request.setAttribute("trans", trans);
			request.setAttribute("users", userService.getAll());
			List <Book> books = new ArrayList<Book>();
			books = bookService.getAvailableBooks();
			books.add(trans.getBook());
			request.setAttribute("books", books);
			request.getRequestDispatcher("edittrans.jsp").forward(request, response);
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
		Transaction trans = hsession.get(Transaction.class, Integer.parseInt(request.getParameter("id")));
		BookService bookService = new BookServiceImpl();
		UserService userService = UserServiceFactory.getLocalUserService();
		trans.setBook(bookService.getBook(Integer.parseInt(request.getParameter("book"))));
		trans.setUser(userService.getUser(Integer.parseInt(request.getParameter("user"))));
		trans.setDateOfBorrow(LocalDate.parse(request.getParameter("dateOfBorrow"), format));
		trans.setExpectedDateOfReturn(LocalDate.parse(request.getParameter("expectedDateOfReturn"), format));
		hsession.getTransaction().commit();
		hsession.close();
		sessionFactory.close();	
		
		response.sendRedirect("lentbooks");
		
	}

}
