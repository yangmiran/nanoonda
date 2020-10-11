package diary.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.model.service.DiaryService;
import diary.model.vo.Diary;

/**
 * Servlet implementation class TempListServlet
 */
@WebServlet("/tlist")
public class TempListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지 기본값 지정
		int currentPage = 1;
		
		//한 페이지당 출력할 목록 갯수 지정
		int limit = 10;
		
		DiaryService dservice = new DiaryService();
		String diaryWriter = request.getParameter("writer");
		
		//전체 목록 갯수 조회
		int listCount = dservice.getTempCount(diaryWriter);
		
		//현재 페이지에 출력할 게시글 목록 조회
		
		ArrayList<Diary> list = dservice.selectTemp(diaryWriter, currentPage, limit);
		
		//뷰에 출력될 총 페이지 수 계산
		int maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지가 속한 그룹의 시작 페이지 수 지정
		//예 : currentPage가 35이면 페이지그룹이 10일 때 시작페이지는 31이 됨.
		int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/diary/tempListView.jsp");
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
	        out.println("<script>alert('임시저장된 글이 없습니다.'); location.href='views/diary/diaryWriteForm.jsp';</script>");
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
