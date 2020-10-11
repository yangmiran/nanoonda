package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberPwdCkViewServlet
 */
@WebServlet("/passdate")
public class MemberPwdCkViewServlet extends HttpServlet {
	private static final long serialVersionUID = 5111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdCkViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 체크 화면 서블릿
		
		//1.
		//2.
		String id = request.getParameter("id");
		
		//3.
		Member member = new MemberService().selectMember(id);
		
		//4.
		RequestDispatcher view = null;
		if(member != null) { //조회 성공시
			
			if(member.getStatus().equals("2")||member.getStatus().equals("3")) {//관리자라면
				view = request.getRequestDispatcher("views/member/adminPwdCk.jsp");
				request.setAttribute("member", member);
				view.forward(request, response);
			}else {//일반회원이면
				view = request.getRequestDispatcher("views/member/memberPwdCk.jsp");
				request.setAttribute("member", member);
				view.forward(request, response);
			}
			
		}else { //조회 실패시
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보 수정실패'); location.href='/nnd/myinfo?id='+ member.getId()</script>");
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
