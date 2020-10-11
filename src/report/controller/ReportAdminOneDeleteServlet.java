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
 * Servlet implementation class ReportAdminOneDeleteServlet
 */
@WebServlet("/radonedelete.ad")
public class ReportAdminOneDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportAdminOneDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자의 신고게시판에서 삭제하기 (체크)
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		ReportService rservice = new ReportService();
		
		int result=rservice.deleteReport(rno);
		
		if(result>0) {
				response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('신고글 삭제 성공'); location.href='/nnd/rlist.ad?page=1'</script>");
	           out.flush();
	           out.close();
		}else {
				response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('신고글 삭제 실패'); history.go(-1); </script>");
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
