package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.*;

/**
 * Servlet implementation class AnnounceServlet
 */
@WebServlet("/AnnounceServlet")
public class AnnounceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnounceServlet() {
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
		String username = (String)request.getSession().getAttribute("username");
		String content = request.getParameter("content");
		if(UserInfo.isAdmin(username)) {
			UserInfo.addAnnouncement(username,content);
			request.setAttribute("alert", "Announcement added!");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} else {
			request.setAttribute("alert", "Announcement couldn't be added, you are not an admin.");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}

}
