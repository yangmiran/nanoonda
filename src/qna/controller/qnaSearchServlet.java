package qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class qnaSearchServlet
 */
@WebServlet("/qsearch")
public class qnaSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public qnaSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id로 회원검색
		//1. 전송온 값에 한글이 있다면 인코딩 처리함
	      request.setCharacterEncoding("utf-8");
	      //뷰로 내보내는 값에 한글이 있다면 인코딩 처리함
	      response.setContentType("text/html; charset=UTF-8");
	      
	      //2. 전송온 값 꺼내서 변수 또는 객체에 기록 저장
	      String action = request.getParameter("action");
	      String keyword = null, beginDate = null, endDate = null;
	      
	      if(action.equals("enrolldate")) {
	         beginDate = request.getParameter("begin");
	         endDate = request.getParameter("end");
	      }else {
	         keyword = request.getParameter("keyword");
	      }
	      
	      //3. 서비스 객체 생성하고, 메소드 실행하고 결과 받기
	      QnaService qservice = new QnaService();
	      ArrayList<Qna> list = null;
	      
		/*
		 * switch(action) { case "id": list = qservice.selectSearchnname(keyword);
		 * break;
		 */
		/*
		 * case "gender": list = mservice.selectSearchGender(keyword); break; case
		 * "age": list = mservice.selectSearchAge(Integer.parseInt(keyword)); break;
		 * case "enrolldate": list =
		 * mservice.selectSearchEnrollDate(Date.valueOf(beginDate),
		 * Date.valueOf(endDate)); break; case "loginok": list =
		 * mservice.selectSearchLoginOk(keyword); break;
		 */
	      }
	      
	      //4. 받은 결과에 대한 뷰 내보내기
	/*
	 * RequestDispatcher view = null; if(list.size() > 0) { view =
	 * request.getRequestDispatcher("views/member/memberListView.jsp");
	 * request.setAttribute("list", list); view.forward(request, response); }else {
	 * view = request.getRequestDispatcher("views/common/error.jsp");
	 * request.setAttribute("message", action + " 검색에 대한 " + keyword +
	 * " 결과가 존재하지 않습니다."); view.forward(request, response); } }
	 */
	   
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
