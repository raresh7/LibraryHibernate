package servlets;

import java.io.IOException;
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
import dao.BookService;
import dao.BookServiceImpl.BookServiceImpl;
import entities.Book;
import entities.User;

/**
 * Servlet implementation class BookList
 */
@WebServlet(urlPatterns={"/booklist"}, name="BookListServlet")
public class BookList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookList() {
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
			if(request.getParameter("delete") != null){			
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session hsession = sessionFactory.openSession();
				hsession.beginTransaction();
				Book delBook = hsession.get(Book.class, Integer.parseInt(request.getParameter("delete")));
				hsession.delete(delBook);
				hsession.getTransaction().commit();
				hsession.close();
				sessionFactory.close();			
			}
			BookService book = new BookServiceImpl();
			List <Book> books =  book.getAll(); 
			request.setAttribute("books", books);
			request.getRequestDispatcher("allbooks.jsp").forward(request, response);
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
