package post.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;


import member.model.vo.Member;
import post.model.dao.PostDao;
import post.model.vo.Post;

public class PostService {
	public PostService() {}

	PostDao pdao = new PostDao();
	
	public int getListCount(String usernn) {
		Connection conn = getConnection();
		int listCount = pdao.getListCount(conn, usernn);
		close(conn);
		return listCount;
	}
	
	public ArrayList<Post> selectSendList(int currentPage, int limit, String usernn) {
			Connection conn = getConnection();
			ArrayList<Post>list=pdao.selectSendList(conn, currentPage, limit, usernn);
			close(conn);
			return list;
	}
	public int getReceiveListCount(String usernn) {
		Connection conn = getConnection();
		int listCount = pdao.getReceiveListCount(conn, usernn);
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Post> selectReceiveList(int currentPage, int limit, String usernn) {
		Connection conn = getConnection();
		ArrayList<Post>list=pdao.selectReceiveList(conn, currentPage, limit, usernn);
		close(conn);
		return list;
	}

	public int getTempListCount(String usernn) {
		Connection conn = getConnection();
		int listCount = pdao.getTempListCount(conn, usernn);
		close(conn);
		return listCount;
	}
	

	public ArrayList<Post> selectTempList(int currentPage, int limit, String usernn) {
		Connection conn = getConnection();
		ArrayList<Post>list=pdao.selectTempList(conn, currentPage, limit, usernn);
		close(conn);
		return list;
	}
	
	public int tempInsertPost(Post post) {
		Connection conn = getConnection();
		int result = pdao.tempInsertPost(conn, post);
		System.out.println("service: "+post);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int sendInsertPost(Post post) {
		Connection conn = getConnection();
		int result = pdao.sendInsertPost(conn, post);
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public Post selectSendOne(int sendno) {
		Connection conn = getConnection();
		Post post = pdao.selectSendOne(conn, sendno);
		close(conn);
		return post;
	}

	public Post selectReceiveOne(int receiveno) {
		Connection conn = getConnection();
		Post post = pdao.selectReceiveOne(conn, receiveno);
		close(conn);
		return post;
	}

	public Post selectTempOne(int tempno) {
		Connection conn = getConnection();
		Post post = pdao.selectTempOne(conn, tempno);
		close(conn);
		return post;
	}

	public int updateTempPost(Post post) {
		Connection conn = getConnection();
		int result = pdao.updateTempPost(conn, post);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Post> selectReceiveNew(String usernn) {
		Connection conn = getConnection();
		ArrayList<Post> list = pdao.selectReceiveNew(conn, usernn);
		close(conn);
		return list;
	}

	public ArrayList<Post> selectSendNew(String usernn) {
		Connection conn = getConnection();
		ArrayList<Post> list = pdao.selectSendNew(conn, usernn);
		close(conn);
		return list;
	}

	public int sendDelete(Post post) {
		Connection conn = getConnection();
		int result = pdao.sendDelete(conn, post);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int receiveDelete(Post post) {
		Connection conn = getConnection();
		int result = pdao.receiveDelete(conn, post);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int tempSendInsertPost(Post post) {
		Connection conn = getConnection();
		int result = pdao.tempSendInsertPost(conn, post);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int tempDelete(int postno) {
		Connection conn = getConnection();
		int result = pdao.tempDelete(conn, postno);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
			close(conn);
			return result;
	}

	public int someOneWrite(Post post) {
		Connection conn = getConnection();
		int result = pdao.someOneWrite(conn, post);
		
		if(result>0)
			commit(conn);
		else
			rollback(conn);
			close(conn);
		
			return result;
	}

	public void addReadCount(int receiveno) {
		Connection conn = getConnection();
		int result = pdao.addReadCount(conn, receiveno);
		if(result>0)
			commit(conn);
		close(conn);
	}

	public int TempSomeOneInsertServlet(Post post) {
		Connection conn = getConnection();
		int result = pdao.TempSomeOneInsertServlet(conn, post);
		
		if(result>0)
			commit(conn);
		else
			rollback(conn);
			close(conn);
		
			return result;
	}

	public ArrayList<Post> postSearch(String nN, String keyword) {
		Connection conn = getConnection();
		ArrayList<Post> list = pdao.postSearch(conn, nN, keyword);
		close(conn);
		return list;
	
	}


	public int sendListDelete(int sendno) {
		Connection conn = getConnection();
		int result = pdao.sendListDelete(conn, sendno);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int receiveListDelete(int receiveno) {
		Connection conn = getConnection();
		int result = pdao.receiveListDelete(conn, receiveno);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int tempListDelete(int tempno) {
		Connection conn = getConnection();
		int result = pdao.tempListDelete(conn, tempno);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	
	
	
	
	public int notReadCount(String usernn) {
		Connection conn = getConnection();
		int notReadCount = pdao.notReadCount(conn, usernn);
		close(conn);
		
		return notReadCount;
	}

	public Post selectNotReadOne(int receiveno) {
		Connection conn = getConnection();
		Post post = pdao.selectNotReadOne(conn, receiveno);
		close(conn);
		return post;
	}

	public ArrayList<Post> notReadList(String nN) {
		Connection conn = getConnection();
		ArrayList<Post> list = pdao.notReadList(conn, nN);
		close(conn);
		return list;

	}
	//뷰로 가기
	public Post moveTo(int pno) {
		// TODO Auto-generated method stub
		return null;
	}
}
