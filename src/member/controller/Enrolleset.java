package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class Enrolleset
 */
@WebServlet("/enrolleset")
public class Enrolleset extends HttpServlet {
	private static final long serialVersionUID = 51111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enrolleset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입_인증번호중복체크
		String resetpass = request.getParameter("resetpass");
		
		int resetpasscount = new MemberService().selectResetpass(resetpass);
		
		
		String returnValue = null;
		if(resetpasscount == 0) {
			returnValue = "enok";
			//System.out.println("if");
		}else {
			returnValue = "enno";
			//System.out.println("else");
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
