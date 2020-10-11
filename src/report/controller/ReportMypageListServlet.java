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

import member.model.vo.Member;
import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class ReportMypageListServlet
 */
@WebServlet("/rmypage")
public class ReportMypageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportMypageListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpServletRequest hrequest = (HttpServletRequest)request;
	     Member loginMember = (Member)hrequest.getSession(false).getAttribute("loginMember");//false : 있는 것 가져와
	     String sender=loginMember.getnName();
		
		// 한 페이지 당 10개 씩  출력되는 신고글 전체 조회 		
		// 페이지 기본 값 지정
		int currentPage = 1;
		// 전송 온 페이지값 추출
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		// 한 페이지당 출력할 목록 갯수 지정
		int limit = 10;

		ReportService rservice = new ReportService();

		// 전체 목록 갯수 조회
		int listCount = rservice.reportMyGetListCount(sender);

		// 밑에 나올 페이지 숫자를 계산해야 함
		int maxPage = (int) ((double) listCount / limit + 0.9);
		// 현재 페이지가 속한 그룹의 시작 페이지 수 지정
		int startPage = ((int) ((double) currentPage / limit + 0.9) - 1) * limit + 1;
		int endPage = startPage + limit - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}
		
		ArrayList<Report> list = rservice.selectReportMyList(currentPage, limit, sender);
		int reportcount = rservice.countMyReport(sender);
		
		RequestDispatcher view = null;
		if(list.size()>0) {
			view = request.getRequestDispatcher("views/report/reportMypageView.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("reportcount", reportcount);
			view.forward(request, response);
		
		}else {
			  response.setContentType("text/html; charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        out.println("<script>alert('신고내역이 없습니다'); history.go(-1);</script>");
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
