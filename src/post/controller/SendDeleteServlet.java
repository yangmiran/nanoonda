package post.controller;

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

import post.model.service.PostService;
import post.model.vo.Post;

/**
 * Servlet implementation class SendDeleteServlet
 */
@WebServlet("/sdelete")
public class SendDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data'");
			view.forward(request, response);
		}
		
		int maxSize = 1024 * 1024 * 10;

	
		String savePath = request.getSession().getServletContext().getRealPath("/resources/pupfiles");
	
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy()); 


		Post post = new Post();

		post.setPostNo(Integer.parseInt(mrequest.getParameter("no")));
		post.setPostTitle(mrequest.getParameter("title"));
		post.setPostSender(mrequest.getParameter("sender"));
		post.setPostReceiver(mrequest.getParameter("receiver"));
		post.setPostContent(mrequest.getParameter("content"));
		
	
		String deleteFlag = mrequest.getParameter("deleteFlag");

		String originalFileName = mrequest.getParameter("pfile");
		String renameFileName = mrequest.getParameter("prfile");

		String newOriginalFileName = mrequest.getFilesystemName("upfile");
		post.setPostFile(newOriginalFileName);

		String newRenameFileName = null;
		if (newOriginalFileName != null) {
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			
			newRenameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())); 
			newRenameFileName += "." + newOriginalFileName.substring(newOriginalFileName.lastIndexOf(".") + 1);

			
			File originFile = new File(savePath + "\\" + newOriginalFileName);
			File renameFile = new File(savePath + "\\" + newRenameFileName);

			
			if (!originFile.renameTo(renameFile)) {
				
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				int data = -1;
				byte[] buffer = new byte[1024]; 
				while ((data = fin.read(buffer, 0, buffer.length)) != -1) { 
					fout.write(buffer, 0, buffer.length);
				}
				fin.close();
				fout.close();
				originFile.delete(); 
			}
			post.setPostRefile(newRenameFileName);
			if (originalFileName != null) {
				new File(savePath + "\\" + renameFileName).delete();
			}
			
		}else if (originalFileName != null && deleteFlag != null && deleteFlag.equals("yes")) {

			
			post.setPostFile(null);
			post.setPostRefile(null);
			
			new File(savePath + "\\" + renameFileName).delete();
		}	else if (originalFileName != null 
				&& (newOriginalFileName==null || 
				originalFileName.equals(newOriginalFileName)
				&& new File(savePath + "\\" + renameFileName).length() == new File(savePath + "\\" + newRenameFileName)
						.length())) {
			
			post.setPostFile(renameFileName);
		} 
		
		int result = new PostService().sendDelete(post);

		// 7. 받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			response.sendRedirect("slist?page=1");
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", post.getPostNo() + "번 편지 삭제 실패!");
			view.forward(request, response);
		}
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
