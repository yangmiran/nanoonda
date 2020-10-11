package report.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
 * Servlet implementation class ReportAdminSearchServlet
 */
@WebServlet("/rsearch.ad")
public class ReportAdminSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportAdminSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//닉네임으로 검색하기
		// 1.전송온 값에 한글 있으면 인코딩
		response.setContentType("text/html; charset=UTF-8");
		
		// 2.전송온 값 꺼내서 변수 또는 객체에 기록 저장
		String keyword = request.getParameter("keyword");
		int page = Integer.parseInt(request.getParameter("page"));
		
		// 3.한 페이지 당 10개 씩  출력-입력된 닉네임에 해당하는 신고글 전체 조회 
		// 페이지 기본 값 지정
		int currentPage = 1;
		// 전송 온 페이지값 추출
		if (page != 0) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		// 한 페이지당 출력할 목록 갯수 10개로 지정
		int limit = 10;
		
		//서비스 객체 생성
		ReportService rservice = new ReportService();

		// 검색한 닉네임에 해당하는 전체 목록 갯수 조회
		int listCount = rservice.reportGetListCount(keyword);

		// 현재 페이지에 출력할 검색한 닉네임에 해당하는 목록 조회
		ArrayList<Report> list = rservice.reportSearch(currentPage, limit, keyword);

		// 밑에 나올 페이지 숫자를 계산해야 함
		int maxPage = (int) ((double) listCount / limit + 0.9);
		// 현재 페이지가 속한 그룹의 시작 페이지 수 지정
		int startPage = ((int) ((double) currentPage / limit + 0.9) - 1) * limit + 1;
		int endPage = startPage + limit - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}
		
		// 4.받은 결과에 대한 뷰 내보내기
		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/report/reportListView.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			view.forward(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('해당하는 회원이 존재하지 않습니다.'); history.go(-1); </script>");
	           out.flush();
	           out.close();

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
