package member.controller;

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

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet("/msearch")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원 관리서비스에서 회원정보 검색 처리용 컨트롤러
		// 전송온 값 꺼내서 변수 또는 객체에 기록 저장
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action"); // action 값에 따라 저장 변수가 달라짐
		String keyword = null, beginDate = null, endDate = null; // 다 keyword로 오는 것이 아님

		// 한 페이지 당 10개 씩 출력되는 전체 회원 조회
		// 페이지 기본 값 지정
		int currentPage = 1;
		// 전송 온 페이지값 추출
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		// 한 페이지당 출력할 목록 갯수 지정
		int limit = 10;

		MemberService mservice = new MemberService();

		// 가져오는 종류에 따라 따로 꺼내와서 변수에 입력하기
		if (action.equals("enrolldate")) {
			beginDate = request.getParameter("begin");
			endDate = request.getParameter("end");
		} else {
			keyword = request.getParameter("keyword");
		}

		// 전체 목록 갯수 조회
		int listCount = 0;

		switch (action) { // action value 값에 따른 각 목록의 갯수 조회
		case "id":
			listCount = mservice.memberGetListCountId(keyword);
			break;
		case "nname":
			listCount = mservice.memberGetListCountNn(keyword);
			break;
		case "enrolldate":
			listCount = mservice.memberGetListCountEd(Date.valueOf(beginDate), Date.valueOf(endDate));
			break;
		}

		// 현재 페이지에 출력할 게시글 목록 조회
		ArrayList<Member> list = null;

		switch (action) { // action value 값
		case "id":
			list = mservice.selectListid(keyword, currentPage, limit);
			break;
		case "nname":
			list = mservice.selectListNn(keyword, currentPage, limit);
			break;
		case "enrolldate":
			list = mservice.selectListEd(Date.valueOf(beginDate), Date.valueOf(endDate), currentPage, limit);
			break;
		}

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
			view = request.getRequestDispatcher("views/member/memberListView.jsp");
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
			out.println("<script>alert('해당하는 회원이 없습니다'); history.go(-1);</script>");
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
