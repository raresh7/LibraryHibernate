package servlets;

import java.io.IOException;
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

import dao.UserService;
import dao.UserServiceImpl.UserServiceFactory;
import entities.User;

/**
 * Servlet implementation class UserList
 */
@WebServlet(urlPatterns={"/userlist"}, name="UserListServlet")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
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
				User delUser = hsession.get(User.class, Integer.parseInt(request.getParameter("delete")));
				hsession.delete(delUser);
				hsession.getTransaction().commit();
				hsession.close();
				sessionFactory.close();			
			}
			UserService userService = UserServiceFactory.getLocalUserService();
			List <User> users =  userService.getAll(); 
			request.setAttribute("users", users);
			request.getRequestDispatcher("allusers.jsp").forward(request, response);
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
