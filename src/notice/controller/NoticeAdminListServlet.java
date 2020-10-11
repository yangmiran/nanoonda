package notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import member.model.vo.Member;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import member.model.service.MemberService;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/anlist")
public class NoticeAdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeAdminListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 관리자용 공지사항 목록보기 처리용 컨트롤러

		// 페이지 기본값 지정
		int currentPage = 1;
		// 전송 온 페이지값 추출
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		// 한 페이지당 출력할 목록 갯수 지정
		int limit = 10;

		NoticeService bservice = new NoticeService();

		// 전체 목록 갯수 조회
		int listCount = bservice.getListCount();

		// 현재 페이지에 출력할 게시글 목록 조회
		ArrayList<Notice> list = bservice.selectList(currentPage, limit);

		// 뷰에 출력될 총 페이지 수 계산 : 게시글이 1개이면 1페이지임 limit가 10이라서 0.9를 더해줌
		int maxPage = (int) ((double) listCount / limit + 0.9);
		// 현재 페이지가 속한 그룹의 시작 페이지 수 지정
		// 예 : cuurentPage가 35이면 시작페이지그룹이 10일때 시작페이지는 31이 됨
		// limit가 10을 가지고 있음
		int startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}
		/* ArrayList<Notice> list = new NoticeService().selectAll(); */

		RequestDispatcher view = null;
		if (list.size() > 0) { // 로그인 안 한 상태 또는 로그인한 일반 회원인 경우
			view = request.getRequestDispatcher("views/notice/noticeAdminListView.jsp");
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
	        out.println("<script>alert('조회된 공지사항이 없습니다.'); location.href='views/diary/diaryWriteForm.jsp';</script>");
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
