package qnareply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qnareply.model.service.QnaReplyService;

/**
 * Servlet implementation class QnaReplyCheckServlet
 */
@WebServlet("/qrcheck")
public class QnaReplyCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 답변여부 체크

		String replytitle = request.getParameter("replytitle");
		
		/*
		 * QnaReplyService qrservice = new QnaReplyService(); int result =
		 * qrservice.updateQnaReplyChange(replytitle);
		 * 
		 * RequestDispatcher view = null; if(result > 0) {
		 * request.setAttribute("replytitle", replytitle); } else {
		 * response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
		 * response.getWriter();
		 * out.println("<script>alert('답변여부를 확인할 수 없습니다'); history.go(-1);</script>");
		 * out.flush(); out.close(); }
		 */
		
		
			
			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
