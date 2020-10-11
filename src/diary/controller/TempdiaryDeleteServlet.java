package diary.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.model.service.DiaryService;

/**
 * Servlet implementation class TempDeleteServlet
 */
@WebServlet(name = "TempdiaryDeleteServlet", urlPatterns = { "/tddel" })
public class TempdiaryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempdiaryDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//받아온 체크박스 dno 배열에 담기
		String[] dno = request.getParameterValues("dno");
		
		//받아온 writer 이름 인코딩
		String writer = URLEncoder.encode(request.getParameter("writer"));
		
		//dno[] 길이만큼 deleteDiary 실행
		int result = 0;		
		for(int i = 0; i < dno.length; i++) {
			int diaryNo = Integer.parseInt(dno[i]);			
			result = new DiaryService().deleteDiary(diaryNo);			
		}
		
		//성공, 실패시 연결화면 처리
		if(result > 0) {
			response.sendRedirect("/nnd/tlist?writer=" + writer);
		} else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('임시저장된 일기 삭제 실패'); history.go(-1);</script>");
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
