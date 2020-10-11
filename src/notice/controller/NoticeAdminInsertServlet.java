
package notice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeAdminInsertServlet
 */
@WebServlet("/aninsert")
public class NoticeAdminInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeAdminInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 관리자용 공지사항 등록

		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form 의 enctype='multipart/form-data'속성 누락됨");
			view.forward(request, response);
		}

		int maxSize = 1024 * 1024 * 10;
		String savePath = request.getSession().getServletContext().getRealPath("/resources/nupfiles");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Notice notice = new Notice();

		notice.setNoticeTitle(mrequest.getParameter("title"));
		notice.setNname(mrequest.getParameter("writer"));
		notice.setNoticeContent(mrequest.getParameter("content"));

		// 첨부파일
		String originalFileName = mrequest.getFilesystemName("ofile"); // getFilesystemName : 파일명만 추출
		notice.setOriginalFilepath(originalFileName); // notice에 파일명 저장

		if (originalFileName != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));

			renameFileName += "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			File originFile = new File(savePath + "\\" + originalFileName); // 원본 파일객체
			File renameFile = new File(savePath + "\\" + renameFileName); // 이름 바꿀 파일 객체

			if (!originFile.renameTo(renameFile)) {

				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(originFile);

				int data = -1;
				byte[] buffer = new byte[1024];
				while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
					fout.write(buffer, 0, buffer.length);
				}
				fin.close();
				fout.close();
				originFile.delete();
			}
			notice.setRenameFilepath(renameFileName);
		}

		// 공지글 등록
		int result = new NoticeService().insertNotice(notice);

		if (result > 0) {
			response.sendRedirect("anlist");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "새 공지사항 등록 처리 실패!");
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