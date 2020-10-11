package post.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.model.service.PostService;
import post.model.vo.Post;

/**
 * Servlet implementation class NotReadDetailServlet
 */
@WebServlet("/pnrdetail")
public class NotReadDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotReadDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int receiveno = Integer.parseInt(request.getParameter("receiveno"));
		
		PostService pservice = new PostService();
		
		pservice.addReadCount(receiveno);
		
		Post post = new PostService().selectNotReadOne(receiveno);
		
		RequestDispatcher view = null;
		if(post!=null) {
			view = request.getRequestDispatcher("views/post/notReadDetailView.jsp");
			request.setAttribute("post", post);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", receiveno+"번 글에 대한 상세보기 요청 실패");
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
