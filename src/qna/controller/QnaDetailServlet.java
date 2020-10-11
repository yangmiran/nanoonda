package qna.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.Qna;
import qnareply.model.service.QnaReplyService;
import qnareply.model.vo.QnaReply;

/**
 * Servlet implementation class QnaDetailServlet
 */
@WebServlet("/qdetail")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자용 문의사항 상세보기

		int qnaNum = Integer.parseInt(request.getParameter("qnum"));

		QnaService qservice = new QnaService();

		Qna qna = qservice.selectQna(qnaNum);

		QnaReply qnareply = new QnaReplyService().selectReply(qnaNum);

		RequestDispatcher view = null;
		if (qna != null) {
			view = request.getRequestDispatcher("views/qna/qnaDetailView.jsp");
			request.setAttribute("qna", qna);
			request.setAttribute("qnareply", qnareply);
			view.forward(request, response);
		} else {
			 response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('문의사항 상세보기 내역이 없습니다.'); history.go(-1);</script>");
	           out.flush();
	           out.close();
		}
	}
}