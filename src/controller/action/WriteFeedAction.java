package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.feed.Feed;
import model.feed.FeedDAO;
import model.member.Member;

public class WriteFeedAction implements Action{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		System.out.println("writeAction,session전:");
		System.out.println(session.getAttribute("user"));
		Member member = (Member) session.getAttribute("user");
		
		System.out.println("writeActioncontent:"+content);
		System.out.println("writeAction,memberid:"+member);
		
		try{
			System.out.println("writeAction2"+content);
			if(content == null || content.trim().length() == 0) {
				System.out.println("writeAction3"+content);
				throw new Exception("입력값이 충분하지 않습니다.");
			}
			
			Date date = new Date();
			System.out.println("writeAction4"+content);
			boolean result = FeedDAO.writeFeed(Feed.builder()
														.content(content)
														.writeday(date)
														.thumbUp(0)
														.memberId(member)
														.build());
			System.out.println("writeAction5"+content+result);
		
			if(result){
				response.sendRedirect("index.html");
			}else{
				throw new Exception("입력값이 충분하지 않습니다.");
			}
		}catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
