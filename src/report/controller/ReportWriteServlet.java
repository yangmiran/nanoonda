package report.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class ReportWriteServlet
 */
@WebServlet("/rinsert.u")
public class ReportWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오픈일기장 글에서의 신고접수 컨트롤러
		//변수 받기
		String reporttitle=request.getParameter("reporttitle"); //신고제목
		String reportcontent = request.getParameter("content");//신고 내용
		String reportsender = request.getParameter("reportsender");//신고자
		int diaryno = Integer.parseInt(request.getParameter("diaryno")); //일기장 글 번호 
		String dwriter = request.getParameter("dwriter"); //신고받은자
		
		Report report = new Report();
		report.setReportTiltle(reporttitle);
		report.setReportContent(reportcontent);
		report.setsenderNname( reportsender);
		report.setDiaryNo(diaryno);        //글번호 같이 보내야
		report.setreceiverNname(dwriter);
		
		ReportService rservice= new ReportService();
		
		int result = rservice.reportInsert(report);
		
		if (result > 0) {
			//신고 접수 완료시
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('신고접수가 완료되었습니다'); history.go(-2);</script>");
	        out.flush();
	        out.close();
		} else {			
			//신고접수 실패 창
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('신고접수에 실패했습니다'); history.go(-2);</script>");
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
