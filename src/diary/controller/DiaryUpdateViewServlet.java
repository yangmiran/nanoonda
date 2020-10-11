package diary.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.model.service.DiaryService;
import diary.model.vo.Diary;



/**
 * Servlet implementation class DiaryUpdateViewServlet
 */
@WebServlet("/dupview")
public class DiaryUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 수정페이지 출력 처리용 컨트롤러
		int diaryNo = Integer.parseInt(request.getParameter("dno"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		String openOk = request.getParameter("openOk");
		
		Diary diary = new DiaryService().selectDiary(diaryNo);
		
		RequestDispatcher view = null;
		if(diary != null) {
			view = request.getRequestDispatcher("views/diary/diaryUpdateView.jsp");
			request.setAttribute("diary", diary);
			request.setAttribute("page", currentPage);
			request.setAttribute("openOk", openOk);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", diaryNo + "번 일기 수정페이지로 이동 실패");
			view.forward(request, response);
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
