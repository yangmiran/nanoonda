package notice.controller;

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

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeNewTop3Servlet
 */
@WebServlet("/antop3")
public class NoticeNewTop3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeNewTop3Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ajax 통신으로 최근 공지글 3개 조회 
		/*
		 * ArrayList<Notice> list = new NoticeService().selectNewTop3();
		 * 
		 * JSONObject sendJSON = new JSONObject(); JSONArray jarr = new JSONArray();
		 * 
		 * for (Notice notice : list) { JSONObject job = new JSONObject(); job.put("no",
		 * notice.getNoticeNo()); job.put("title",
		 * URLEncoder.encode(notice.getNoticeTitle(), "utf-8")); job.put("date",
		 * notice.getNoticeDate().toString());
		 * 
		 * jarr.add(job); }
		 * 
		 * sendJSON.put("list", jarr);
		 * 
		 * response.setContentType("application/json; charset=utf-8");
		 * 
		 * PrintWriter out = response.getWriter(); out.write(sendJSON.toJSONString());
		 * out.flush(); out.close();
		 */
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
