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

import dao.TransactionService;
import dao.TransactionServiceImpl.TransactionServiceImpl;
import entities.Transaction;
import entities.User;

/**
 * Servlet implementation class ReturnBooks
 */
@WebServlet(urlPatterns={"/returnbooks"}, name="ReturnBooksServlet")
public class ReturnBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBooks() {
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
			TransactionService transService = new TransactionServiceImpl();
			List<Transaction> validTrans = transService.getAllActive();
			request.setAttribute("trans", validTrans);
			request.getRequestDispatcher("returnbook.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("dateOfReturn").length() >= 8 && request.getParameterValues("receive")!=null){
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
			LocalDate date = LocalDate.parse(request.getParameter("dateOfReturn"), format);
			String[] idString = request.getParameterValues("receive");
			List <Integer> ids = new ArrayList<Integer>();
			for(int i=0;i<idString.length;i++){
				ids.add(Integer.parseInt(idString[i]));
				System.out.println(ids.get(i) + " - " + date);
			}
			TransactionService transService = new TransactionServiceImpl();
			transService.returnBooks(ids, date);
			response.sendRedirect("returnbooks");
			
		}
		else{
			request.setAttribute("msg", "Please select the received books!");
			request.getRequestDispatcher("returnbook.jsp").forward(request, response);
			}
	

		
			
	}

}
