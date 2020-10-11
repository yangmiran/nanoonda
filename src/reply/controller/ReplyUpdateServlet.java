package reply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.model.service.ProService;
import reply.model.service.ReplyService;
import reply.model.vo.Reply;

/**
 * Servlet implementation class ReplyUpdateServlet
 */
@WebServlet("/rupdate")
public class ReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String content = request.getParameter("content");
		
		boolean checkResult = new ProService().proCheck(content);				
		
		if(checkResult == false) {
			Reply reply = new Reply();
			reply.setReplyContent(request.getParameter("content"));
			int replyNo = Integer.parseInt(request.getParameter("rno"));
			int dno = Integer.parseInt(request.getParameter("dno"));
			int page = Integer.parseInt(request.getParameter("page"));
			reply.setReplyNo(replyNo);

			int result = new ReplyService().updateReply(reply);

			if (result > 0) {
				RequestDispatcher view = request.getRequestDispatcher("/ddetail");
				request.setAttribute("dno", dno);
				request.setAttribute("page", page);
				view.forward(request, response);
			} else {
				response.setContentType("text/html; charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        out.println("<script>alert('댓글 수정에 실패하였습니다.'); history.go(-1);</script>");
		        out.flush();
		        out.close();
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('금지어가 포함된 게시글은 등록할 수 없습니다.'); history.go(-1);</script>");
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
