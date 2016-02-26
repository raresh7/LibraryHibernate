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

import appSpecs.DBServices;
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
		{	DBServices services = new DBServices();
			Transaction trans = services.selectTransById(Integer.parseInt(request.getParameter("id")));
			System.out.println(trans);
			request.setAttribute("trans", trans);
			request.setAttribute("users", services.selectUser());
			List <Book> books = new ArrayList<Book>();
			books.add(services.selectBookById(trans.getBookid()));
			books.addAll(services.selectAllBooksAvailable());
			//System.out.println(services.selectBookById(trans.getBookid()).getAuthor());			
			request.setAttribute("books", books);
			request.getRequestDispatcher("edittrans.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		DBServices services = new DBServices();
		services.updateTransById(	Integer.parseInt(request.getParameter("id")),
									Integer.parseInt(request.getParameter("user")),
									Integer.parseInt(request.getParameter("book")),
									LocalDate.parse(request.getParameter("dateOfBorrow"), format),
									LocalDate.parse(request.getParameter("expectedDateOfReturn"), format),
									null);
		response.sendRedirect("lentbooks");
		
	}

}
