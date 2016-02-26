package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appSpecs.AppSettings;
import appSpecs.DBServices;
import entities.User;

/**
 * Servlet implementation class EditUser
 */
@WebServlet(urlPatterns={"/edituser"}, name="EditUserServlet")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User usr;
		usr = (User)session.getAttribute("loggedUser");
		if(usr == null || usr.getIsAdmin() == false)
			response.sendRedirect("index.jsp");
		else
		{	
			DBServices services = new DBServices();
			User user = services.selectUserById(Integer.parseInt(request.getParameter("id"))).get(0);
			request.setAttribute("user", user);
			request.getRequestDispatcher("edituser.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBServices services = new DBServices();
		services.updateUserById(Integer.parseInt(request.getParameter("id")),
								request.getParameter("name"),
								request.getParameter("ssn"),
								request.getParameter("address"),
								Boolean.valueOf((request.getParameter("isAdmin").equals("true"))));
		response.sendRedirect("userlist");
	}

}
