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

import appSpecs.DBServices;
import entities.Transaction;
import entities.User;

/**
 * Servlet implementation class BorrowedBooks
 */
@WebServlet(urlPatterns={"/borrowed"}, name="BorrowedBooksServlet")
public class BorrowedBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowedBooks() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user;
		user = (User)session.getAttribute("loggedUser");
		
		if(user == null)
			response.sendRedirect("index.jsp");
		else
		{
			List<Transaction> borrowed = new ArrayList<Transaction>();
			DBServices services = new DBServices();
			Boolean all;
			
			if(request.getParameter("all") != null){
				all = true;		
				borrowed = services.selectAllBorrowedByUser(((User)session.getAttribute("loggedUser")).getId());
			}
			else{
				all = false;				
				borrowed = services.selectActiveBorrowedByUser(((User)session.getAttribute("loggedUser")).getId());
				}
			
			request.setAttribute("all", all);
			request.setAttribute("borrowedBooks", borrowed);
			request.getRequestDispatcher("borrowedbooks.jsp").forward(request, response);
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
