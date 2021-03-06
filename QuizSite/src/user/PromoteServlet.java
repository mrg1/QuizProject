package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserInfo;

/**
 * Servlet implementation class PromoteServlet
 */
@WebServlet("/PromoteServlet")
public class PromoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String username = (String)request.getSession().getAttribute("username");
		if(UserInfo.isAdmin(username)) {
			UserInfo.changeAdmin(user,true);
			request.setAttribute("alert", user + " has been promoted to admin.");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} else {
			request.setAttribute("alert", "Action not processed, you are not an admin.");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}

}
