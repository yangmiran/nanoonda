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
 * Servlet implementation class MyInfoServlet
 */
@WebServlet("/myinfo")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 5111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내정보 보기
		 HttpServletRequest hrequest = (HttpServletRequest)request;
	        Member loginMember = (Member)hrequest.getSession(false).getAttribute("loginMember");//false : 있는 것 가져와
	        String id=loginMember.getId();
		
		Member member = new MemberService().selectMember(id);
		RequestDispatcher view = null;
		
		if(member != null) {
			
			if(Integer.parseInt(member.getStatus())==3 ||Integer.parseInt(member.getStatus())==2) {
				//관리자라면		
				view = request.getRequestDispatcher("views/member/adminmyInfoPage.jsp");
				request.setAttribute("member", member);
				view.forward(request, response);
			}else {
				//회원이면
				view = request.getRequestDispatcher("views/member/myInfoPage.jsp");
				request.setAttribute("member", member);
				view.forward(request, response);
			
			}

		}else { //실패시
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인해주세요'); location.href='/nnd/views/member/myInfoPage.jsp';</script>");
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
