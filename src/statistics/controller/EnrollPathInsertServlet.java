package statistics.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import statistics.model.service.StatisticsService;
import statistics.model.vo.Statistics;

/**
 * Servlet implementation class EnrollReasonInsertServlet
 */
@WebServlet("/seinsert")
public class EnrollPathInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnrollPathInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 가입경로 등록
		String[] str = new StatisticsService().insertEnrollPath();

		String en1= str[0];
		String en2= str[1];
		String en3= str[2];
		String en4= str[3];
		String en5= str[4];
		
		if (str != null) {

			// 탈퇴이유 등록
			String[] str2 = new StatisticsService().insertReasonQuit();
			
			String del1 = str2[0];
			String del2 = str2[1];
			String del3 = str2[2];
			String del4 = str2[3];

			RequestDispatcher view = null;
			view = request.getRequestDispatcher("views/member/adminMainPage.jsp");
			request.setAttribute("en1", en1);
			request.setAttribute("en2", en2);
			request.setAttribute("en3", en3);
			request.setAttribute("en4", en4);
			request.setAttribute("en5", en5);
			request.setAttribute("del1", del1);
			request.setAttribute("del2", del2);
			request.setAttribute("del3", del3);
			request.setAttribute("del4", del4);
			view.forward(request, response);
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
