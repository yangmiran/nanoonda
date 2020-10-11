package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberEmailCkServlet
 */
@WebServlet("/emaildateCk")
public class MemberEmailCkServlet extends HttpServlet {
	private static final long serialVersionUID = 5111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEmailCkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 찾기 이메일 중복 체크
		String email = request.getParameter("email");
		
		int emailcount = new MemberService().selectEmail(email);
		
		String returnValue = null;
		
		
		if(emailcount == 0) {
			returnValue = "ook";
			//System.out.println("if");
		    
		}else {
			returnValue = "dup";
			//System.out.println("else");
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
		/*
		 * String email = request.getParameter("email"); MemberService mservice = new
		 * MemberService(); Member emailMember = mservice.selectEmail(email);
		 * 
		 * System.out.println(email); if(emailMember != null) {
		 * System.out.println("서블릿"); HttpSession session = request.getSession();
		 * session.setAttribute("emailMember", emailMember);
		 * response.sendRedirect("/nnd/views/email/searchPw.jsp"); }else {
		 * response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
		 * response.getWriter(); out.
		 * println("<script>alert('가입한 이메일이 아닙니다.'); location.href='/nnd/views/member/idpwdSerch.jsp#tabs-2'</script>"
		 * ); out.flush(); }
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
