package report.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.model.service.DiaryService;
import diary.model.vo.Diary;
import post.model.service.PostService;
import post.model.vo.Post;

/**
 * Servlet implementation class ReportMoveServlet
 */
@WebServlet("/rmoveto.ad")
public class ReportMoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportMoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//해당 게시글로 가기
		int dno = Integer.parseInt(request.getParameter("dno"));
		int pno = Integer.parseInt(request.getParameter("pno"));
		String writer = request.getParameter("writer");
		
		if(dno>0) { //다이어리 글이면
			DiaryService dservice = new DiaryService();
			Diary diary = new Diary();
			diary =dservice.moveTo(dno);
			if(diary!=null) {//글이 삭제되지 않았다면
				//뷰로
				
			}else {//삭제되었다면
				//알럿창
			}
		}else {//우편글이면
			PostService pservice = new PostService();
			Post post = new Post();
			post = pservice.moveTo(pno);
			if(post!=null) {//글이 삭제되지 않았다면
				//뷰로
			}else {//삭제되었다면
				//알럿창
			}
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
