package report.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class ReportAdminTypeSelectServlet
 */
@WebServlet("/rtypeselect")
public class ReportAdminTypeSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportAdminTypeSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 처리상태에 따라 리스트 값 출력하기
		String type= request.getParameter("type");
		System.out.println(type);
		ReportService rservice = new ReportService();
		ArrayList<Report> list = rservice.reportTypeSelectList(type);
		
		RequestDispatcher view = null;
		if(list.size()>0) {
			view = request.getRequestDispatcher("views/report/reportListView.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
		}else {
			response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('해당하는 신고내역이 없습니다'); history.go(-1);</script>");
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
