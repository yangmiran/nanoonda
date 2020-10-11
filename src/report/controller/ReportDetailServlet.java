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
 * Servlet implementation class ReportDetailServlet
 */
@WebServlet("/rdetail.u")
public class ReportDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유저 마이페이지의 신고게시판에서 제목 클릭시 상세보기 나오는 컨트롤러
		//변수 받기
		int reportno = Integer.parseInt(request.getParameter("rno"));
		
		ReportService rservice = new ReportService();
		Report report = rservice.reportDetail(reportno);
		
		RequestDispatcher view = null;
		if(report!=null) { //상세 보기 내역 있으면 출력
			view = request.getRequestDispatcher("views/report/reportDetailView.jsp");
			request.setAttribute("report", report);
			view.forward(request, response);
			
		}else { //상세 보기 불러오기 실패
			   response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('선택된 신고글 상세보기 실패'); history.go(-1);</script>");
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
