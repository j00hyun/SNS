package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.feed.FeedDAO;


public class AllViewFeedAction implements Action {
 
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		
		try {
			request.setAttribute("list", FeedDAO.getAllFeed());
			System.out.println("ActionFactory-list요청받음.Allview : "+FeedDAO.getAllFeed().size());
			url = "list.jsp";
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
			System.out.println("Allview페이지 에러임.");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
