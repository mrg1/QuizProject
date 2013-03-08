package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.*;
import db.UserInfo;

/**
 * Servlet implementation class SendRequestServlet
 */
@WebServlet("/SendRequestServlet")
public class SendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendRequestServlet() {
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
		String from = (String)request.getSession().getAttribute("username");
		String to = request.getParameter("to");
		if(UserInfo.userExists(to)) {
			UserInfo.addMessage(new Request(to,from,""));
			request.setAttribute("alert", "Request successfully sent!");
			request.getRequestDispatcher("friends.jsp").forward(request, response);
		} else {
			request.setAttribute("alert", "Request couldn't be sent, user " + to + " doesn't exist.");
			request.getRequestDispatcher("friends.jsp").forward(request, response);
		}
	}

}
