package post.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import diary.model.vo.Diary;
import member.model.vo.Member;

import static common.JDBCTemp.*;
import post.model.vo.Post;

public class PostDao {
	public PostDao() {
	}

	public int getListCount(Connection conn, String usernn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from post where post_sender = ? and send_del_check = 'N' and save_check ='N' order by send_date desc";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, usernn);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Post> selectSendList(Connection conn, int currentPage, int limit, String usernn) {
		ArrayList<Post> list = new ArrayList<Post>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from "
				+ "(select rownum rnum, post_no, post_sender, post_receiver, post_title, post_content, post_file, post_refile, send_date, read_check, send_del_check, receive_del_check, save_check from "
				+ "(select * from post where post_sender =? and send_del_check = 'N' and save_check ='N' order by send_date desc)) "
				+ "where rnum >= ? and rnum<=?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, usernn);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Post post = new Post();
				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));

				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getReceiveListCount(Connection conn, String usernn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from post where post_receiver = ? and receive_del_check = 'N' and save_check ='N' order by send_date desc";

		try {
			
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, usernn);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
		
	}

	public ArrayList<Post> selectReceiveList(Connection conn, int currentPage, int limit, String usernn) {
		ArrayList<Post> list = new ArrayList<Post>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from "
				+ "(select rownum rnum, post_no, post_sender, post_receiver, post_title, post_content, post_file, post_refile, send_date, read_check, send_del_check, receive_del_check, save_check from "
				+ "(select * from post where post_receiver =? and receive_del_check = 'N' and save_check ='N' order by send_date desc)) "
				+ "where rnum >= ? and rnum<=?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, usernn);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Post post = new Post();
				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));

				list.add(post);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getTempListCount(Connection conn, String usernn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from post where post_sender = ? and send_del_check = 'N' and save_check ='Y' order by send_date desc";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, usernn);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Post> selectTempList(Connection conn, int currentPage, int limit, String usernn) {
		ArrayList<Post> list = new ArrayList<Post>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from "
				+ "(select rownum rnum, post_no, post_sender, post_receiver, post_title, post_content, post_file, post_refile, send_date, read_check, send_del_check, receive_del_check, save_check from "
				+ "(select * from post where post_sender =? and send_del_check = 'N' and save_check ='Y' order by send_date desc)) "
				+ "where rnum >= ? and rnum<=?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, usernn);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Post post = new Post();
				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));

				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int tempInsertPost(Connection conn, Post post) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "INSERT INTO POST VALUES (POST_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, 'Y')";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, post.getPostSender());
			pstmt.setString(2, post.getPostReceiver());
			pstmt.setString(3, post.getPostTitle());
			pstmt.setString(4, post.getPostContent());
			pstmt.setString(5, post.getPostFile());
			pstmt.setString(6, post.getPostRefile());

			result = pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int sendInsertPost(Connection conn, Post post) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "INSERT INTO POST VALUES (POST_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, post.getPostSender());
			pstmt.setString(2, post.getPostReceiver());
			pstmt.setString(3, post.getPostTitle());
			pstmt.setString(4, post.getPostContent());
			pstmt.setString(5, post.getPostFile());
			pstmt.setString(6, post.getPostRefile());

			result = pstmt.executeUpdate();
			System.out.println("Dao받는이: " + post.getPostReceiver());
			System.out.println("Dao보내는이:" + post.getPostSender());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Post selectSendOne(Connection conn, int sendno) {
		Post post = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from post where post_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sendno);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				post = new Post();

				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return post;
	}

	public Post selectReceiveOne(Connection conn, int receiveno) {
		Post post = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from post where post_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, receiveno);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				post = new Post();

				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return post;
	}

	public Post selectTempOne(Connection conn, int tempno) {
		Post post = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from post where post_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tempno);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				post = new Post();

				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return post;

	}

	public int updateTempPost(Connection conn, Post post) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update post set post_receiver=?, post_title=?, post_content=?, post_file=?, post_refile=? where post_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, post.getPostReceiver());
			pstmt.setString(2, post.getPostTitle());
			pstmt.setString(3, post.getPostContent());
			pstmt.setString(4, post.getPostFile());
			pstmt.setString(5, post.getPostRefile());
			pstmt.setInt(6, post.getPostNo());

			result = pstmt.executeUpdate();
			System.out.println("TempDao받는이: " + post.getPostReceiver());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Post> selectReceiveNew(Connection conn, String usernn) {
		ArrayList<Post>list = new ArrayList<Post>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ( "
				+ "select rownum rnum, post_no, post_sender, post_title, send_date from ( "
				+ "select * from post where post_receiver=? and receive_del_check ='N' and save_check='N' order by send_date desc)) where rnum >= 1 and rnum <=5";
				
			try {
			
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, usernn);
				rset=pstmt.executeQuery();
				while (rset.next()) {

					Post post = new Post();
					post.setPostNo(rset.getInt("post_no"));
					post.setPostSender(rset.getString("post_sender"));
					post.setPostTitle(rset.getString("post_title"));
					post.setSendDate(rset.getDate("send_date"));
				
					list.add(post);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
			close(pstmt);	
			}
			
		return list;
	}

	public ArrayList<Post> selectSendNew(Connection conn, String usernn) {
		ArrayList<Post>list = new ArrayList<Post>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ( "
				+ "select rownum rnum, post_no, post_receiver, post_title, send_date from ( "
				+ "select * from post where post_sender=? and send_del_check ='N' and save_check='N' order by send_date desc)) where rnum >= 1 and rnum <=5";
				
			try {
				
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, usernn);
				rset=pstmt.executeQuery();
				while (rset.next()) {

					Post post = new Post();
					post.setPostNo(rset.getInt("post_no"));
					post.setPostReceiver(rset.getString("post_receiver"));
					post.setPostTitle(rset.getString("post_title"));
					post.setSendDate(rset.getDate("send_date"));
				
					list.add(post);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
			close(pstmt);	
			}
			
		return list;
	}

	public int sendDelete(Connection conn, Post post) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update post set send_del_check ='Y' where post_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, post.getPostNo());

			result = pstmt.executeUpdate();
			
			System.out.println("번호 " + post.getPostNo());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int receiveDelete(Connection conn, Post post) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update post set receive_del_check ='Y' where post_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, post.getPostNo());

			result = pstmt.executeUpdate();
			
			System.out.println("번호 " + post.getPostNo());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int tempSendInsertPost(Connection conn, Post post) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update post set save_check ='N' where post_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, post.getPostNo());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int tempDelete(Connection conn, int postno) {
		int result = 0; 
		PreparedStatement pstmt = null;
		
		String query = "delete from post where post_no= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, postno);
			
			result =pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int someOneWrite(Connection conn, Post post) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO POST VALUES(POST_SEQ.NEXTVAL, ?, (SELECT * FROM ( SELECT NNAME FROM MEMBER WHERE STATUS='1' AND NNAME NOT IN ? ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <=1 ), ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, 'N')";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, post.getPostSender());
			pstmt.setString(2, post.getPostSender());
			pstmt.setString(3, post.getPostTitle());
			pstmt.setString(4, post.getPostContent());
			pstmt.setString(5, post.getPostFile());
			pstmt.setString(6, post.getPostRefile());
			
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int addReadCount(Connection conn, int receiveno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update post set read_check= read_check+1 where post_no= ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, receiveno);
			
			result = pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int TempSomeOneInsertServlet(Connection conn, Post post) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO POST VALUES(POST_SEQ.NEXTVAL, ?, (SELECT * FROM ( SELECT NNAME FROM MEMBER WHERE STATUS='1' AND NNAME NOT IN ? ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <=1 ), ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, 'Y')";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, post.getPostSender());
			pstmt.setString(2, post.getPostSender());
			pstmt.setString(3, post.getPostTitle());
			pstmt.setString(4, post.getPostContent());
			pstmt.setString(5, post.getPostFile());
			pstmt.setString(6, post.getPostRefile());
			
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Post> postSearch(Connection conn, String nN, String keyword) {
		ArrayList<Post> list = new ArrayList<Post>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from post where (post_sender =? or post_receiver=?) and post_title like ? and send_del_check='N' and  receive_del_check='N' and save_check='N' order by send_date desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, nN);
			pstmt.setString(2, nN);
			pstmt.setString(3, "%" + keyword + "%");
						
			rset = pstmt.executeQuery();
			System.out.println(nN);
				
				while(rset.next()) {
				
				Post post = new Post();
				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));
								
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int sendListDelete(Connection conn, int sendno) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String query = "update post set send_del_check='Y' where post_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
		pstmt.setInt(1, sendno);
			
			result=pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int receiveListDelete(Connection conn, int receiveno) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String query = "update post set receive_del_check='Y' where post_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
		pstmt.setInt(1, receiveno);
			
			result=pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int tempListDelete(Connection conn,  int tempno) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String query = "update post set send_del_check='Y', receive_del_check='Y', save_check='N' where post_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
		pstmt.setInt(1, tempno);
					
			result=pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
	public int notReadCount(Connection conn, String usernn) {
		int notReadCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from post where post_receiver=? and read_check = 1 and receive_del_check='N' and save_check='N'";

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, usernn);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				notReadCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return notReadCount;

	}

	public Post selectNotReadOne(Connection conn, int receiveno) {
		Post post = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from post where post_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, receiveno);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				post = new Post();

				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return post;
	}

	public ArrayList<Post> notReadList(Connection conn, String nN) {
		ArrayList<Post>list = new ArrayList<Post>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from post where post_receiver=? and read_check='1' and receive_del_check='N' and save_check='N' order by send_date desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, nN);
									
			rset = pstmt.executeQuery();
						
			while (rset.next()) {

				Post post = new Post();
				post.setPostNo(rset.getInt("post_no"));
				post.setPostSender(rset.getString("post_sender"));
				post.setPostReceiver(rset.getString("post_receiver"));
				post.setPostTitle(rset.getString("post_title"));
				post.setPostContent(rset.getString("post_content"));
				post.setPostFile(rset.getString("post_file"));
				post.setPostRefile(rset.getString("post_refile"));
				post.setSendDate(rset.getDate("send_date"));
				post.setReadCheck(rset.getInt("read_check"));
				post.setSendDelCheck(rset.getString("send_del_check"));
				post.setReceiverDelCheck(rset.getString("receive_del_check"));
				post.setSaveCheck(rset.getString("save_check"));
			
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
		close(pstmt);	
		}
		
	return list;

}
	
}
