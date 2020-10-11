package reply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reply.model.service.ReplyService;

/**
 * Servlet implementation class HateInsertServlet
 */
@WebServlet("/hate")
public class HateInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HateInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String counter = request.getParameter("counter");
		int page = Integer.parseInt(request.getParameter("page"));
		int dno = Integer.parseInt(request.getParameter("dno"));
		int replyNo = Integer.parseInt(request.getParameter("rno"));
		int hateCount = Integer.parseInt(request.getParameter("hateCount"));
		ReplyService rservice = new ReplyService();
		
		int checkResult =  rservice.checkHate(counter, replyNo);
		if(checkResult > 0) {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('이미 비추천하신 댓글입니다.'); history.go(-1);</script>");
	        out.flush();
	        out.close();
		} else {			
			if(hateCount == 4) {
				int result = rservice.deleteReply(replyNo);
				if (result > 0) {
					response.sendRedirect("/nnd/ddetail?dno="+ dno + "&page=" + page);
				} else {
					response.setContentType("text/html; charset=UTF-8");
			        PrintWriter out = response.getWriter();
			        out.println("<script>alert('댓글 싫어요에 실패하였습니다.'); history.go(-1);</script>");
			        out.flush();
			        out.close();
				}
			} else {
				rservice.updateHateCount(replyNo);
				int result = rservice.insertHate(counter, replyNo);			
				if (result > 0) {
					response.sendRedirect("/nnd/ddetail?dno="+ dno + "&page=" + page);
				} else {
					response.setContentType("text/html; charset=UTF-8");
			        PrintWriter out = response.getWriter();
			        out.println("<script>alert('댓글 싫어요에 실패하였습니다.'); history.go(-1);</script>");
			        out.flush();
			        out.close();
				}			
			}			
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
