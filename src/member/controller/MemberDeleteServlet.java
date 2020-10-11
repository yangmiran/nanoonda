package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import statistics.model.service.StatisticsService;
import statistics.model.vo.Statistics;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mdel")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 5111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원탈퇴

		String id = request.getParameter("id");
		int result = new MemberService().deleteMember(id);
		//System.out.println(id);
		
		//탈퇴 이유
		Statistics statistics = new Statistics();
		//System.out.println(request.getParameter("seck"));
		statistics.setReasonquit(request.getParameter("seck"));
		new StatisticsService().insertStatistics(statistics);
		//System.out.println(result);
		if(result > 0) {
			/*
			 * response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
			 * response.getWriter(); out.println("<script>alert('탈퇴되었습니다.');</script>");
			 * out.flush();
			 */
			
			response.sendRedirect("/nnd/index.jsp");
			/*
			 * RequestDispatcher view = request.getRequestDispatcher("/nnd/index.jsp");
			 * request.setAttribute("id", id); view.forward(request, response);
			 */
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원님의 탈퇴 요청이 실패되었습니다.');</script>");
			out.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
