package post.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.model.service.PostService;

/**
 * Servlet implementation class ReceiveListDeleteServlet
 */
@WebServlet("/prldel")
public class ReceiveListDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiveListDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      //받아온 체크박스 no 배열에 담기
	      String[] no = request.getParameterValues("no");
	      

	      //no[] 길이만큼 delete 실행
	      int result = 0;      
	      for(int i = 0; i < no.length; i++) {
	         int receiveno = Integer.parseInt(no[i]);         
	         result = new PostService().receiveListDelete(receiveno);         
	      }
		
		RequestDispatcher view = null;
		
		if (result > 0) {
			view = request.getRequestDispatcher("rlist?page=1");
			view.forward(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('선택한 편지 삭제 실패.'); document.location.href='views/post/receiveListView.jsp';</script>");
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
