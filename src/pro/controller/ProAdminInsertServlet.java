package pro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import pro.model.service.ProService;
import pro.model.vo.Pro;

/**
 * Servlet implementation class ProAdminInsertServlet
 */
@WebServlet("/pinsert.ad")
public class ProAdminInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProAdminInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 관리자 권한으로 금지어 등록 컨트롤러

		// 1. 전송 온 값을 객체에 저장
		Pro pro = new Pro();
		pro.setProContent(request.getParameter("procontent"));

		HttpServletRequest hrequest = (HttpServletRequest) request;
		Member loginMember = (Member) hrequest.getSession(false).getAttribute("loginMember");// false : 있는 것 가져와
		pro.setAdminNname(loginMember.getnName());

		ProService pservice = new ProService();
		// 중복 값 있는 지 확인하기
		int count = pservice.dupleCheckPro(pro);
		if (count > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 금지어는 이미 등록되어 있습니다.'); location.href='/nnd/plist.ad?page=1'</script>");
			out.flush();
			out.close();
		} else {

			int result = pservice.proInsert(pro);

			// 2. 뷰로 리턴하기
			RequestDispatcher view = null;
			if (result > 0) { // 조회 성공시
				response.sendRedirect("/nnd/plist.ad?page=1");

			} else { 
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('금지어 등록에 실패하였습니다'); location.href='/nnd/plist.ad?page=1'</script>");
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
