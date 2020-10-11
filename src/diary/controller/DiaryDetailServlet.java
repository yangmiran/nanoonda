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
import reply.model.service.ReplyService;
import reply.model.vo.Reply;

/**
 * Servlet implementation class DiaryDetailServlet
 */
@WebServlet("/ddetail")
public class DiaryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//다이어리 상세보기 처리용 컨트롤러
		int diaryNo = Integer.parseInt(request.getParameter("dno"));
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		};
		
		DiaryService dservice = new DiaryService();
		
		//조회수 1증가 처리 : update
		dservice.addReadCount(diaryNo);		
		
		//글번호에 대한 게시글 조회 : select
		Diary diary = dservice.selectDiary(diaryNo);		
		
		//해당 글번호에 대한 댓글 조회
        ArrayList<Reply> replyList = new ReplyService().selectAll(diaryNo);
        request.setAttribute("replyList", replyList);
        
		if(diary != null) {
			RequestDispatcher view = request.getRequestDispatcher("views/diary/diaryDetailView.jsp");
			request.setAttribute("diary", diary);
			request.setAttribute("currentPage", currentPage);
			view.forward(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('일기 상세보기 실패'); history.go(-1);</script>");
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
