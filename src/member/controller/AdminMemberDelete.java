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
 * Servlet implementation class AdminMemberDelete
 */
@WebServlet("/adeleteuser")
public class AdminMemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원탈퇴

				String id = request.getParameter("bno");
				int result = new MemberService().deleteMember(id);
		
				if(result > 0) {
			
					 response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
					 response.getWriter(); 
					 out.println("<script>alert('탈퇴되었습니다.'); history.go(-1);</script>");
					out.flush();
					
				}else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('탈퇴 요청이 실패되었습니다.');  history.go(-1); </script>");
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
