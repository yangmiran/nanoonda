package pro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.model.service.ProService;
import report.model.service.ReportService;

/**
 * Servlet implementation class ProAdminDeleteServlet
 */
@WebServlet("/pdelete.ad")
public class ProAdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProAdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//체크 박스 선택한 금지어 삭제
		  String[] pno = request.getParameterValues("pno");
		    
	      //rno[] 길이만큼 deleteReport 실행
	      int result = 0;      
	      for(int i = 0; i < pno.length; i++) {
	         int prono = Integer.parseInt(pno[i]);         
	         result = new ProService().deletePro(prono); 
	         if(result==0) {//한개라도 성공 못하면 실패처리
	        	 break;
	         }
	      }
	      
	      //성공, 실패시 연결화면 처리
	      if(result > 0) {
	    	  response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('선택된 금지어 삭제 성공'); location.href='/nnd/plist.ad?page=1'</script>");
	           out.flush();
	           out.close();
	      } else {
	         response.setContentType("text/html; charset=UTF-8");
	           PrintWriter out = response.getWriter();
	           out.println("<script>alert('선택된 금지어 삭제 실패'); history.go(-1);</script>");
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
