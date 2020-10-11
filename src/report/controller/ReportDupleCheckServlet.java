package report.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class ReportDupleCheckServlet
 */
@WebServlet("/rduple.u")
public class ReportDupleCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDupleCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오픈 일기장에서 같은 글 여러번 신고 방지 컨트롤러, 내가 쓴 글 신고 불가, 
		
		//변수 받기		 	
		HttpServletRequest hrequest = (HttpServletRequest)request;
        Member loginMember = (Member)hrequest.getSession(false).getAttribute("loginMember");//false : 있는 것 가져와
        String reportsender=loginMember.getnName();//신고자
        
        
        
		String dwriter = request.getParameter("dwriter");//신고 받는 사람
		int diaryno = Integer.parseInt(request.getParameter("dno")); //일기장 글 번호 
		
		ReportService rservice= new ReportService();
		
		 int listcount = rservice.reportCheck(reportsender,diaryno);
		
		
		RequestDispatcher view;
		
		if (listcount > 0) {
			//중복 신고임 
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('이미 신고하신 글 입니다.'); history.go(-1);</script>");
	        out.flush();
	        out.close();
	     //   System.out.println("중복신고");
		} else {			
			//중복 신고 아님
			view = request.getRequestDispatcher("views/report/reportWriteForm.jsp");
			request.setAttribute("reportsender", reportsender);
			request.setAttribute("dwriter", dwriter);
			request.setAttribute("diaryno", diaryno);
			view.forward(request, response);
		//	 System.out.println("중복신고아님");
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
