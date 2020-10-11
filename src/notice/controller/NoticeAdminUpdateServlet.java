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
 * Servlet implementation class NoticeAdminUpdateServlet
 */
@WebServlet("/anupdate")
public class NoticeAdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeAdminUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 관리자용 공지사항 수정 처리용 컨트롤러
		
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

		notice.setNoticeNo(Integer.parseInt(mrequest.getParameter("no")));
		notice.setNoticeTitle(mrequest.getParameter("title"));
		notice.setNname(mrequest.getParameter("writer"));
		notice.setNoticeContent(mrequest.getParameter("content"));

		String deleteFlag = mrequest.getParameter("deleteFlag");

		String originalFileName = mrequest.getParameter("ofile");
		String renameFileName = mrequest.getParameter("rfile");

		String newOriginalFileName = mrequest.getFilesystemName("upfile");
		notice.setOriginalFilepath(newOriginalFileName);

		String newRenameFileName = null;
		if (newOriginalFileName != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

			newRenameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));

			newRenameFileName += "." + newOriginalFileName.substring(newOriginalFileName.lastIndexOf(".") + 1);

			File originFile = new File(savePath + "\\" + newOriginalFileName);
			File renameFile = new File(savePath + "\\" + newRenameFileName);

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
			notice.setRenameFilepath(newRenameFileName);

			if (originalFileName != null) {
				new File(savePath + "\\" + renameFileName).delete();
			}
		} else if (originalFileName != null && originalFileName.equals(newOriginalFileName)
				&& new File(savePath + "//" + renameFileName).length() == new File(savePath + "\\" + newRenameFileName)
						.length()) {
			notice.setOriginalFilepath(originalFileName);
			notice.setRenameFilepath(renameFileName);
		} else if (originalFileName != null && deleteFlag != null && deleteFlag.equals("yes")) {
			notice.setOriginalFilepath(null);
			notice.setRenameFilepath(null);
			new File(savePath + "\\" + renameFileName).delete();
		}

		int result = new NoticeService().updateNotice(notice);
		if (result > 0) {
			response.sendRedirect("anlist");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", notice.getNoticeNo() + "번 공지사항 수정 실패!");
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