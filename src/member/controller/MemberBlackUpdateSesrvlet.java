package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberBlackUpdateSesrvlet
 */
@WebServlet("/mblackup.ad")
public class MemberBlackUpdateSesrvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberBlackUpdateSesrvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 블랙리스트 해제 
		if( request.getParameterValues("bno")==null) {
			response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('선택 사항이 없습니다.'); location.href='/nnd/mblacklist?'</script>");
	           out.flush();
	           out.close();
		}
		String [] arr = request.getParameterValues("bno");
		
		MemberService rservice = new MemberService();
		String id="";
		int result=0;
		for(int i = 0 ; i<arr.length; i++) {
			id = (arr[i]);
			result=rservice.blacklistUpn(id);
		
		}
		if(result>0) {
			response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('블랙리스트 해제 성공'); location.href='/nnd/rlist.ad?page=1?'</script>");
	           out.flush();
	           out.close();
		}else {
			response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('블랙리스트 해제 실패'); location.href='/nnd/mblacklist?'</script>");
	           out.flush();
	           out.close();
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
