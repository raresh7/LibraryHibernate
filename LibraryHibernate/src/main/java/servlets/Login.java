package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appSpecs.AppSettings;
import dao.UserService;
import dao.UserServiceImpl.UserServiceFactory;
import entities.User;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns={"/login"}, name="LoginServlet")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("index.jsp");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		User user;
		UserService userService = UserServiceFactory.getPicUserService();
		user = userService.getUser(request.getParameter("user"));
		if(user != null){
			AppSettings appSettings = new AppSettings();
			session.setAttribute("appSettings", appSettings);
			session.setAttribute("loggedUser", user);
		}
		else{
			System.out.println("no user");
			request.setAttribute("msg", "The user Name not valid! <br/>");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
		//services.closeConnection();
	}

}
