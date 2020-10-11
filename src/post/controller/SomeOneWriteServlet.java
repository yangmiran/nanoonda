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
 * Servlet implementation class SomeOneWriteServlet
 */
@WebServlet("/psinsert")
public class SomeOneWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SomeOneWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//랜덤보내기용 컨트롤러
		
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
			
		
		post.setPostTitle(title);
		post.setPostContent(content);
		post.setPostSender(mrequest.getParameter("nn"));
		
		String sOfileName=mrequest.getFilesystemName("pfile");
		post.setPostFile(sOfileName);
	
		if(sOfileName != null) {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
			String sRfileName=sdf.format(new java.sql.Date(System.currentTimeMillis()));
			sRfileName += "." +sOfileName.substring(sOfileName.lastIndexOf(".")+1);
			
			File originFile = new File(savePath + "\\" + sOfileName);
			File renameFile = new File(savePath + "\\" + sRfileName); 
			
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
				post.setPostRefile(sRfileName);
		}   
		
		int result = new PostService().someOneWrite(post);
		
		if(result>0) {
			response.sendRedirect("slist?page=1");
		}else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('제목, 내용은 필수 입력 항목입니다.');document.location.href='views/post/someOneWriteForm.jsp';</script>");
	        out.flush();
	        out.close();
		}
	}else {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('금지어가 포함된 편지는 보낼 수 없습니다.');document.location.href='views/post/someOneWriteForm.jsp';</script>");
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
