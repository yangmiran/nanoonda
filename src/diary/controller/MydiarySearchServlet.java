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
import member.model.vo.Member;

/**
 * Servlet implementation class MydiarySearchServlet
 */
@WebServlet("/mdsearch")
public class MydiarySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MydiarySearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest hrequest = (HttpServletRequest)request;
        Member loginMember = (Member)hrequest.getSession(false).getAttribute("loginMember");//false : 있는 것 가져와
        String diaryWriter = loginMember.getnName();
		
		String keyword = request.getParameter("keyword");
		ArrayList<Diary> list = new DiaryService().searchMydiary(diaryWriter, keyword);
		
		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/diary/searchListView.jsp");
			request.setAttribute("list", list);
			request.setAttribute("keyword", keyword);
			view.forward(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('검색된 글이 없습니다.'); history.go(-1);</script>");
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
