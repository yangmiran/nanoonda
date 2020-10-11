package report.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.service.ReportService;

/**
 * Servlet implementation class ReportDeleteServlet
 */
@WebServlet("/rdelete.u")
public class ReportDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유저의 체크 박스 선택된 신고게시글 삭제
		 //받아온 체크박스 rno 배열에 담기
	      String[] rno = request.getParameterValues("rno");
	    
	      //rno[] 길이만큼 deleteReport 실행
	      int result = 0;      
	      for(int i = 0; i < rno.length; i++) {
	         int reportno = Integer.parseInt(rno[i]);         
	         result = new ReportService().deleteReport(reportno);         
	      }
	      
	      //성공, 실패시 연결화면 처리
	      if(result > 0) {
	    	  response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('선택된 신고글 삭제 성공'); location.href='/nnd/rmypage?page=1'</script>");
	           out.flush();
	           out.close();
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('선택된 신고글 삭제 실패'); history.go(-1);</script>");
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
