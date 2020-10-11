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
 * Servlet implementation class MemberPwdCkServlet
 */
@WebServlet("/passdateCk")
public class MemberPwdCkServlet extends HttpServlet {
	private static final long serialVersionUID = 5111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdCkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 체크
		String pwd = request.getParameter("pwd");
	//	System.out.println(pwd);
		
		MemberService mservice = new MemberService();
		Member pwdMember = mservice.selectPwd(pwd);
		
		if(pwdMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("pwdMember", pwdMember);
			if(Integer.parseInt(pwdMember.getStatus())==2 ||Integer.parseInt(pwdMember.getStatus())==3) {//관리자면
				response.sendRedirect("/nnd/views/member/adminUpdatePage2.jsp");
			}else {//아니면
				response.sendRedirect("/nnd/views/member/memberUpdatePage.jsp");
			}
			
		}else {
			
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('패스워드가 틀립니다.'); history.go(-1); </script>");
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
