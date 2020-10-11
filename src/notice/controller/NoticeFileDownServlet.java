package notice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeFileDownServlet
 */
@WebServlet("/nfdown")
public class NoticeFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeFileDownServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공지사항 첨부파일

		String savePath = request.getSession().getServletContext().getRealPath("/resources/nupfiles");
		request.setCharacterEncoding("utf-8");
		String originalFileName = request.getParameter("ofile");
		String renameFileName = request.getParameter("rfile");
		File readFile = new File(savePath = "\\" + renameFileName);
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(readFile));
		ServletOutputStream downOut = response.getOutputStream();
		response.setContentType("text/plain; charset=utf-8");
		response.addHeader("Content-Disposition",
				"attachment; filename=\"" + new String(originalFileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
		response.setContentLength((int) readFile.length());
		int data = -1;
		while ((data = bin.read()) != -1) {
			downOut.write(data);
			downOut.flush();
		}
		downOut.close();
		bin.close();
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
