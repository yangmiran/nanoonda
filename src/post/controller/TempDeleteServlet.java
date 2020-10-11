package post.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.model.service.PostService;

/**
 * Servlet implementation class TempDeleteServlet
 */
@WebServlet("/ptdelete")
public class TempDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 임시저장편지 삭제 처리용 컨트롤러
				int postno = Integer.parseInt(request.getParameter("postno"));
				if (new PostService().tempDelete(postno) > 0) {
					
					String renameFileName = request.getParameter("prfile");
					if (renameFileName != null) {
						String savePath = request.getSession().getServletContext().getRealPath("/resources/pupfiles");
						new File(savePath + "\\" + renameFileName).delete();
					}
					response.sendRedirect("/nnd/ptlist?page=1");
				} else {
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", postno + "번 편지 삭제 실패");
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
