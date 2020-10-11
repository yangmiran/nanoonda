package qnareply.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnareply.model.service.QnaReplyService;
import qnareply.model.vo.QnaReply;

/**
 * Servlet implementation class QnaReplyListServlet
 */
@WebServlet("/qrlist")
public class QnaReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 조회
		int currentPage = 1;
		if (request.getParameter("page") != null) {
		currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int limit = 10;
		QnaReplyService qrservice = new QnaReplyService();
		int listCount = qrservice.getListCount();

		ArrayList<QnaReply> list = qrservice.selectList(currentPage, limit);
		int maxPage = (int)((double)listCount / limit + 0.9);
		int startPage = (((int)((double)currentPage / limit + 0.9)) -1) * limit + 1;
		int endPage = startPage + limit - 1;
				
		if(maxPage < endPage) {
			endPage = maxPage;
		}
				
		RequestDispatcher view = null;
		if(list.size() > 0) {
		view = request.getRequestDispatcher("views/qnareply/qnaReplyListView.jsp");
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);
					
		view.forward(request, response);
		} else {
		view = request.getRequestDispatcher("views/common/error.jsp");
		request.setAttribute("message", currentPage + "페이지에 대한 목록 조회 실패!");
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
