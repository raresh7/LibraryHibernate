package servlets;

import java.io.IOException;

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
 * Servlet implementation class EditBook
 */
@WebServlet(urlPatterns={"/editbook"}, name="EditBookServlet")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBook() {
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
		    BookService bookService = new BookServiceImpl();
			Book book = bookService.getBook(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("book", book);
			request.getRequestDispatcher("editbooks.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session hsession = sessionFactory.openSession();
		hsession.beginTransaction();
		Book book = hsession.get(Book.class, Integer.parseInt(request.getParameter("id")));
		book.setAuthor(request.getParameter("author"));
		book.setTitle(request.getParameter("title"));
		book.setIsbn(request.getParameter("isbn"));
		book.setState(request.getParameter("state"));
		hsession.getTransaction().commit();
		hsession.close();
		sessionFactory.close();	
		
		response.sendRedirect("booklist");
	
	}

}
