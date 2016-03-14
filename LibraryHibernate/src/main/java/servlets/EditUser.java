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
			
			User user = User.getUser(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("user", user);
			request.getRequestDispatcher("edituser.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session hsession = sessionFactory.openSession();
		hsession.beginTransaction();
		User user = hsession.get(User.class, Integer.parseInt(request.getParameter("id")));
		user.setAddress(request.getParameter("address"));
		user.setIsAdmin(Boolean.valueOf((request.getParameter("isAdmin").equals("true"))));
		user.setName(request.getParameter("name"));
		user.setSsn(request.getParameter("ssn"));
		hsession.getTransaction().commit();
		hsession.close();
		sessionFactory.close();			
		response.sendRedirect("userlist");
	}

}
