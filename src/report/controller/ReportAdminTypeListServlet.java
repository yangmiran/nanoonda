package report.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class ReportAdminTypeListServlet
 */
@WebServlet("/rtylist.ad")
public class ReportAdminTypeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportAdminTypeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajax 요청으로 미처리 상태인 신고글 반환하는 컨트롤러
			ArrayList<Report> list = new ReportService().reportTypeSelectList();
				
			//1. 전송객체 생성 /문자 배열 받는 객체 생성
			JSONObject sendJSON = new JSONObject();
			JSONArray jarr = new JSONArray();
				
			for(Report report : list) {
				JSONObject obj = new JSONObject();//report 저장용 json 객체 만들기
					
				obj.put("no", report.getReportNo()); //신고 번호
				obj.put("title", URLEncoder.encode(report.getReportTiltle(),"utf-8")); //신고제목
				obj.put("sender", URLEncoder.encode(report.getsenderNname(),"utf-8")); //신고자
				obj.put("receiver", URLEncoder.encode(report.getreceiverNname(),"utf-8")); //신고 받은자
				obj.put("date", report.getReportRegDate().toString()); //신고 날짜
					
				jarr.add(obj);
				}//for each 구문 끝
				
				sendJSON.put("list", jarr);
				
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(sendJSON.toJSONString());
				out.flush();
				out.close();
				
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
