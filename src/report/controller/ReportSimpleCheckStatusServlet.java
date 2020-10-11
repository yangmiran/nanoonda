package report.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.model.service.ReportService;

/**
 * Servlet implementation class ReportSimpleCheckStatusServlet
 */
@WebServlet("/rsimplech")
public class ReportSimpleCheckStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportSimpleCheckStatusServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 처리완료 처리 하기 전에 처리 완료 처리 됬는지 확인
		// 받아온 체크박스 배열에 담기
		String[] rno = request.getParameterValues("rno");

		
		if(rno==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('선택된 값이 없습니다.'); location.href='/nnd/rlist.ad?page=1'</script>");
			out.flush();
			out.close();
		}
		// rno[] 길이만큼 checkReport 실행
		// 처리 상태 확인
		int count = 0;
		for (int i = 0; i < rno.length; i++) {
			int reportno = Integer.parseInt(rno[i]);

			count = new ReportService().reportCheckStatus(reportno);
			if (count == 0) {// 한개라도 처리 완료된 상태가 있을 경우
				break;
			}
		}

		if (count == 0) {// 처리 완료 상태가 하나라도 있을경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 처리 완료된 건이 있습니다.'); location.href='/nnd/rlist.ad?page=1'</script>");
			out.flush();
			out.close();
		} else { // 모두 미처리 상태인 경우 '처리완료'로 처리

			// rno[] 길이만큼 updateComDateReport 실행
			int result = 0;
			for (int i = 0; i < rno.length; i++) {
				int reportno = Integer.parseInt(rno[i]);
				result = new ReportService().reportComUpdate(reportno);
				if (result == 0) { // 처리완료로 업데이트 실패한것이 하나라도 있다면
					break;
				}
			}
			if (result > 0) {// 처리 완료
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('처리 완료에 성공했습니다.'); location.href='/nnd/rlist.ad?page=1' ;</script>");
				out.flush();
				out.close();
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('처리 완료에 실패 했습니다.'); location.href='/nnd/rlist.ad?page=1' ;</script>");
				out.flush();
				out.close();
			}

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
