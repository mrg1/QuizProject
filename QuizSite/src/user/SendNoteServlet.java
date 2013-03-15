package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.*;
import message.*;

/**
 * Servlet implementation class SendNoteServlet
 */
@WebServlet("/SendNoteServlet")
public class SendNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendNoteServlet() {
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
		String content = request.getParameter("content");		
		if(from.equals(to)) {
			request.setAttribute("alert", "Message couldn't be sent, you cannot message yourself.");
			request.getRequestDispatcher("sendMessage.jsp").forward(request, response);
		} else if(UserInfo.userExists(to)) {
			UserInfo.addMessage(new Note(to,from,content));
			request.setAttribute("alert", "Message successfully sent!");
			request.getRequestDispatcher("sendMessage.jsp").forward(request, response);
		} else {
			request.setAttribute("alert", "Message couldn't be sent, user " + to + " doesn't exist.");
			request.getRequestDispatcher("sendMessage.jsp").forward(request, response);
		}
	}

}
