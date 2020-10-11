package qnareply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import qnareply.model.service.QnaReplyService;
import qnareply.model.vo.QnaReply;

/**
 * Servlet implementation class QnaReplyInsertServlet
 */
@WebServlet("/qrinsert")
public class QnaReplyInsertServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public QnaReplyInsertServlet() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // 관리자용 답글 작성

      QnaReply qnareply = new QnaReply(); 
      qnareply.setQnaNo(Integer.parseInt(request.getParameter("qnum")));
      qnareply.setReplyTitle(request.getParameter("title"));
      qnareply.setNname(request.getParameter("writer"));
      qnareply.setReplyContent(request.getParameter("content"));

      int result = new QnaReplyService().insertQnaReply(qnareply);
      
      if (result > 0) {
         response.sendRedirect("/nnd/aqlist");
      } else {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>alert('문의사항 등록 실패'); history.go(-1);</script>");
         out.flush();
         out.close();
      }

   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}