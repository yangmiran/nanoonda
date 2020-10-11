package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDorUpdateServlet
 */
@WebServlet("/mdorupdate.ad")
public class MemberDorUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberDorUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 휴면회원 해제
		if (request.getParameterValues("id") == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('휴면해제할 회원을 선택하세요'); location.href='/nnd/mdorlist?page=1' </script>");
			out.flush();
		} else {
			// 받아온 체크박스 id 배열에 담기
			String[] idarr = request.getParameterValues("id");

			// id[] 길이만큼 update 실행
			int result = 0;
			for (int i = 0; i < idarr.length; i++) {
				String id = idarr[i];
				result = new MemberService().updateDorMember(id);
			}

			if (result > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('휴면회원 해제 요청에 성공하였습니다.'); location.href='/nnd/mdorlist?page=1' </script>");
				out.flush();
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('휴면회원 해제 요청에 실패하였습니다.'); location.href='/nnd/mdorlist?page=1' </script>");
				out.flush();
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
