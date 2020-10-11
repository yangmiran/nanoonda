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
 * Servlet implementation class MemberResetPwdServlet
 */
@WebServlet("/mresetpwd")
public class MemberResetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberResetPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 임시 비밀번호로 바꾸기
		
		String id = request.getParameter("id");
		MemberService mservice = new MemberService();
		Member member = mservice.selectMember(id);
		String pwd = member.getResetpass();
		String email = member.getEmail();
		String username = member.getnName();
		System.out.println(id + pwd);
		
		int result = mservice.updatePwd(pwd, id);
		if(result > 0) {
			response.sendRedirect("/nnd/views/email/searchPw.jsp?email=" + email + "&usename=" + username);
//			RequestDispatcher view = request.getRequestDispatcher("/nnd/views/email/searchPw.jsp");
//			request.setAttribute("email", email);
//			request.setAttribute("username", username);
//			view.forward(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('오류'); location.href='/nnd/views/member/myInfoPage.jsp'</script>");
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
