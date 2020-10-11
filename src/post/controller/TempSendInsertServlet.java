package post.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import post.model.service.PostService;
import post.model.vo.Post;
import pro.model.service.ProService;

/**
 * Servlet implementation class TempSendInsertServlet
 */
@WebServlet("/ptsinsert")
public class TempSendInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempSendInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//임시저장함에서 보내기용 컨트롤러
				RequestDispatcher view = null;
				if(!ServletFileUpload.isMultipartContent(request)) {
					view=request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", "form의 enctype='multipart/form-data'");
					view.forward(request, response);
				}
			
				int maxSize = 1024 * 1024 * 10;
				String savePath = request.getSession().getServletContext().getRealPath("/resources/pupfiles");
				MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			
				String title =mrequest.getParameter("title");
			      String content = mrequest.getParameter("content");
			     
			      boolean checkResult = new ProService().proCheck(content, title);
			      if(checkResult == false) {
				
				
				Post post = new Post(); 
				post.setPostNo(Integer.parseInt(mrequest.getParameter("no")));
				post.setPostTitle(title);
				post.setPostReceiver(mrequest.getParameter("receiver"));
				post.setPostContent(content);
				post.setPostSender(mrequest.getParameter("nn"));
				String ptOfileName=mrequest.getFilesystemName("pfile");
				post.setPostFile(ptOfileName);
				
				if(ptOfileName != null) {
					SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
					String ptRfileName=sdf.format(new java.sql.Date(System.currentTimeMillis()));
					ptRfileName += "." +ptOfileName.substring(ptOfileName.lastIndexOf(".")+1);
					
					File originFile = new File(savePath + "\\" + ptOfileName);
					File renameFile = new File(savePath + "\\" + ptRfileName); 
					
					if(!originFile.renameTo(renameFile)) {
						FileInputStream fin = new FileInputStream(originFile);
						FileOutputStream fout = new FileOutputStream(renameFile);
						int data = -1;
						byte[] buffer = new byte[1024];  
						while((data=fin.read(buffer, 0, buffer.length)) != -1) {  
							fout.write(buffer, 0, buffer.length);
						}
						fin.close();
						fout.close();
						originFile.delete(); 
						}      
						post.setPostRefile(ptRfileName);
				}   
				
				int result = new PostService().tempSendInsertPost(post);
				
				// 7. 받은 결과로 성공/실패 페이지 내보내기
				if(result>0) {
					response.sendRedirect("slist?page=1");
				}else {
					response.setContentType("text/html; charset=UTF-8");
			        PrintWriter out = response.getWriter();
			        out.println("<script>alert('제목, 받는사람, 내용은 필수 입력 항목입니다.');document.location.href='ptlist?page=1';</script>");
			        out.flush();
			        out.close();
				}
	}else {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('금지어가 포함된 편지는 보낼 수 없습니다.');document.location.href='ptlist?page=1';</script>");
        out.flush();
        out.close();
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
