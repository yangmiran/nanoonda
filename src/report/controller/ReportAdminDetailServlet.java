package report.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.service.ReportService;
import report.model.vo.Report;



/**
 * Servlet implementation class RreportAdminDetailServlet
 */
@WebServlet("/rdetail.ad")
public class ReportAdminDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportAdminDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 신고 목록에서 제목 클릭시 신고 내용 상세보기 컨트롤러
		
		//신고번호 받기
		int reportno= Integer.parseInt(request.getParameter("rno"));
		
		//db와 연동하기
		ReportService rservice = new ReportService();
		Report report = new Report();
		report = rservice.reportDetail(reportno);
		
		
		RequestDispatcher view = null;
		if(report!=null) {
			view = request.getRequestDispatcher("views/report/reportAdminDetailView.jsp");
			request.setAttribute("report", report);
			view.forward(request, response);
			
		}else {
			 response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('신고 내역 상세보기에 실패하였습니다.'); history.go(-1); '</script>");
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
