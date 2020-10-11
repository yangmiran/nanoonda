package statistics.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.model.vo.Member;
import statistics.model.service.VisitorService;

/**
 * Servlet implementation class loginCountServlet
 */
@WebServlet("/aslogin")
public class loginCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginCountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인 성공하면 아이디랑 비밀번호 받아오기
		// 오늘 하루 총 방문자 수

		VisitorService vservice = new VisitorService();
		int logincount = vservice.loginCount();
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");

		session.setAttribute("loginMember", loginMember);
		session.setAttribute("logincount", logincount);

		if(loginMember.getStatus().equals("1") || loginMember.getStatus().equals("4")) {//일반회원이면
			response.sendRedirect("/nnd/calenderlist?writer=" +URLEncoder.encode(loginMember.getnName(),"UTF-8"));
		}else {//관리자일 때
			response.sendRedirect("/nnd/seinsert");
			
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
