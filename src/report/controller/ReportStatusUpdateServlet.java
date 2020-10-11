package report.controller;

import static common.JDBCTemp.commit;
import static common.JDBCTemp.rollback;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import report.model.service.ReportService;

/**
 * Servlet implementation class ReportStatusUpdateServlet
 */
@WebServlet("/rupdate.ad")
public class ReportStatusUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportStatusUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 경고주는 컨트롤러
		// 변수 받기
		String[] rno = request.getParameterValues("rno"); // 신고번호
		String[] receiver = request.getParameterValues("writer"); // 경고 받아야할 사람

		// 경고하러 가기
		MemberService mservice = new MemberService();
		
		int dccount = 0;
		for (int i = 0; i < rno.length; i++) {
			String writer= receiver[i];
			dccount = mservice.updateDcCount(writer);
			if (dccount == 0) {// 신고카운트 업데이트 에러가 하나라도 있을 경우
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('경고 주기에 실패하였습니다.'); history.go(-1);</script>");
				out.flush();
				out.close();			
			}else if(dccount >= 5){// 신고 횟수가 5번이 넘어가면 로그인 제한
				int result = 0;
				for (int j = 0; j < rno.length; j++) {
					String writer2= receiver[i];
					result=mservice.updateLoginLimit(writer2);
					if(result==0) {//로그인 제한 실패 
						break;
					}
				}
			
				if (result > 0) {// 경고 누적 &로그인 제한성공
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('경고 주기에 성공했습니다.'); location.href='/nnd/rlist.ad?page=1' ;</script>");
					out.flush();
					out.close();
				} else {// 경고주기에 성공 but, 로그인 제한 실패 << 확인
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('경고 주기에 성공했습니다.'); location.href='/nnd/rlist.ad?page=1;</script>");
					out.flush();
					out.close();

				}
			} else {// 신고 횟수가 추가되고 누적 신고 횟수가 5번이 안된 경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('경고 주기에 성공했습니다.'); history.go(-1);</script>");
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
