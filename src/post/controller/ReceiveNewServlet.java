package post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import post.model.service.PostService;
import post.model.vo.Post;
import member.model.vo.Member;
import member.model.service.MemberService;


/**
 * Servlet implementation class ReceiveNewServlet
 */
@WebServlet("/rnew")
public class ReceiveNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReceiveNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			String usernn = loginMember.getnName();
			
			ArrayList<Post>list = new PostService().selectReceiveNew(usernn);
				
				JSONObject sendJSON=new JSONObject();
				
				JSONArray jarr = new JSONArray();
				
				for(Post post: list) {
						
					JSONObject job = new JSONObject();
					
					job.put("no", post.getPostNo());
					job.put("title", URLEncoder.encode(post.getPostTitle(),"utf-8"));
					job.put("sender", URLEncoder.encode(post.getPostSender(),"utf-8"));
				
					job.put("date", post.getSendDate().toString());

					jarr.add(job);
					
				}
				
				
				sendJSON.put("list", jarr);
				
				
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(sendJSON.toString());
				out.flush();
				out.close();
			}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
