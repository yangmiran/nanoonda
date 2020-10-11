package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import statistics.model.service.StatisticsService;
import statistics.model.vo.Statistics;

/**
 * Servlet implementation class EnrollServlet
 */
@WebServlet("/minsert")
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 5111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//암호화 회원가입
		request.setCharacterEncoding("utf-8");
				
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setEmail(request.getParameter("email"));
		member.setnName(request.getParameter("nname"));
		
		/*
		 * member.setMemberNo(Integer.parseInt(request.getParameter("member_no")));
		 * member.setEnrollDate(Date.valueOf(request.getParameter("enroll_date")));
		 * member.setLastModified(Date.valueOf(request.getParameter("lastmodified")));
		 * member.setDcCount(Integer.parseInt(request.getParameter("dc_count")));
		 * member.setLoginLimit(request.getParameter("login_limit"));
		 * member.setStatus(request.getParameter("status"));
		 */
		
		//회원가입경로
		Statistics statistics = new Statistics();
		//System.out.println(request.getParameter("sellist"));
		String sellist =request.getParameter("sellist");
		statistics.setEnrollpath(sellist);
	
		new StatisticsService().insertStatistics(statistics);
		
		int result = new MemberService().insertMember(member);
		if (result > 0) {
			/* response.sendRedirect("/nnd/views/member/loginPage.jsp"); */
//					response.sendRedirect("/nnd/views/email/emailok.jsp");
			
			
			RequestDispatcher view = request.getRequestDispatcher("views/member/loginPage.jsp");
			request.setAttribute("member", member);
			view.forward(request, response);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 가입 실패! 입력한 정보를 다시 확인해주세요.'); location.href='/nnd/views/member/enrollPage.jsp';</script>");
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
