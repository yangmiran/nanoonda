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

import diary.model.service.CalenderService;
import diary.model.vo.Diary;
import member.model.vo.Member;

/**
 * Servlet implementation class CalenderListServlet
 */
@WebServlet("/calenderlist")
public class CalenderListServlet extends HttpServlet {
	private static final long serialVersionUID = 5111L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalenderListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // 캘린더 다이어리 리스트 조회
	      request.setCharacterEncoding("UTF-8");
	       HttpServletRequest hrequest = (HttpServletRequest)request;
	           Member loginMember = (Member)hrequest.getSession(false).getAttribute("loginMember");//false : 있는 것 가져와
	          String diaryWriter=loginMember.getnName();
	   System.out.println(diaryWriter);
	      
	      ArrayList<Diary> list = new CalenderService().selectCalender(diaryWriter);
	      ArrayList<Diary> oplist = new CalenderService().selectYesCalender(diaryWriter);
	      
	      
	      RequestDispatcher view = null;
	      
	      if(list.size() >= 0) { //전체 조회 성공시
	         //System.out.println("성공");
	         view = request.getRequestDispatcher("views/diary/mydiary.jsp");
	         request.setAttribute("list", list);
	         request.setAttribute("oplist", oplist);
	         view.forward(request, response);
	      }else { //전체 조회 실패시
	         response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('캘린더 실패'); location.href='views/diary/mydiary.jsp';</script>");
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
