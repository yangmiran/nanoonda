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
 * Servlet implementation class TempUpdateServlet
 */
@WebServlet("/ptupview")
public class TempUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postno=Integer.parseInt(request.getParameter("postno"));
		
		Post post = new PostService().selectTempOne(postno);
		RequestDispatcher view = null;
		if(post !=null) {
			view = request.getRequestDispatcher("views/post/tempUpdateForm.jsp");
					request.setAttribute("post", post);
			view.forward(request, response);
		}else {
			view=request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", post + "번 글에 대한 수정페이지 요청");
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
