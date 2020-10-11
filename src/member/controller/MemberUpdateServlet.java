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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/mupdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 5111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 회원 정보 수정
		request.setCharacterEncoding("utf-8");
		
		Member member = new Member();
		
		member.setId(request.getParameter("id"));
		member.setnName(request.getParameter("nname"));
		member.setPwd(request.getParameter("pwd"));
		member.setEmail(request.getParameter("email"));
		
		int result = new MemberService().updateMember(member);

		
		 if(result > 0) { response.sendRedirect("/nnd/myinfo?id=" + member.getId());
		 }else { response.setContentType("text/html; charset=UTF-8"); 
			 PrintWriter out = response.getWriter(); out.
			 println("<script>alert('회원정보 수정 실패'); location.href='/nnd/myinfo'</script>");
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
