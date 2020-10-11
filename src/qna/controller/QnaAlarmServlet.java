package qna.controller;

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

import qna.model.service.QnaService;
import qna.model.vo.Qna;


/**
 * Servlet implementation class QnaAlarmServlet
 */
@WebServlet("/qalarm")
public class QnaAlarmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaAlarmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajax 요청으로 답변쓰지 않은 문의사항글  반환하는 컨트롤러
		ArrayList<Qna> list = new QnaService().qnaAlarmList();
			
		//1. 전송객체 생성 /문자 배열 받는 객체 생성
		JSONObject sendJSON = new JSONObject(); //전송객체 생성
		JSONArray jarr = new JSONArray();//문자 배열 받는 객체 생성
			
		for(Qna qna : list) {
			JSONObject obj = new JSONObject();//qna저장용 json 객체 만들기
				
			obj.put("no", qna.getQnaNo()); //qna 번호
			obj.put("title", URLEncoder.encode(qna.getQnaTitle(),"utf-8")); //qna제목	
			obj.put("writer", URLEncoder.encode(qna.getNname(),"utf-8")); //qna 작성자
			obj.put("date",qna.getQnaDate().toString()); //문의사항 작성일
				
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
