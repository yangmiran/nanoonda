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
 * Servlet implementation class ReportUpdateServlet
 */
@WebServlet("/reportup")
public class ReportUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 유저의 마이페이지에서의 신고글 수정하기 전 값 불러오기
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		ReportService rservice = new ReportService();
		Report report =rservice.reportSelectOne(rno);
		
		RequestDispatcher view = null;
		if(report != null) {
			view = request.getRequestDispatcher("views/report/reportUpdateView.jsp");
			request.setAttribute("report", report);
			view.forward(request, response);
		}else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('신고글 수정 불가'); history.go(-1); </script>");
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
