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

import appSpecs.AppSettings;
import appSpecs.DBServices;
import entities.Book;
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
			DBServices services = new DBServices();

			List <Book> avBooks = new ArrayList<Book>();
			avBooks = services.selectAllBooksAvailable();
			request.setAttribute("books", avBooks);
			request.setAttribute("users", services.selectUser());
			request.getRequestDispatcher("newtrans.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AppSettings appSettings = (AppSettings) session.getAttribute("appSettings");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		DBServices services = new DBServices();
		
//		Transaction trans = new Transaction(	appSettings.getUserById(Integer.parseInt(request.getParameter("user"))),
//												appSettings.getBookById(Integer.parseInt(request.getParameter("book"))),
//												LocalDate.parse(request.getParameter("dateOfBorrow"), format),
//												LocalDate.parse(request.getParameter("expectedDateOfReturn"), format),
//												Transaction.nextId);
//		appSettings.getTrans().add(trans);
		services.insertTrans(	Integer.parseInt(request.getParameter("user")),
								Integer.parseInt(request.getParameter("book")),
								LocalDate.parse(request.getParameter("dateOfBorrow"), format),
								LocalDate.parse(request.getParameter("expectedDateOfReturn"), format));
		response.sendRedirect("addtrans");
				
	}

}
