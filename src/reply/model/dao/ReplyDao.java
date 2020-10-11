package reply.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import reply.model.vo.Reply;

public class ReplyDao {
	public ReplyDao() {}

	public ArrayList<Reply> selectAll(Connection conn, int diaryNo) {
		ArrayList<Reply> replyList = new ArrayList<Reply>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from reply where diary_no = ? order by reply_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, diaryNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply reply = new Reply();
				reply.setReplyNo(rset.getInt("reply_no"));
				reply.setDiaryNo(diaryNo);
				reply.setReplyContent(rset.getString("reply_content"));
				reply.setReplyWriter(rset.getString("reply_writer"));
				reply.setReplyDate(rset.getDate("reply_date"));
				reply.setLikeCount(rset.getInt("like_count"));
				reply.setHateCount(rset.getInt("hate_count"));
				reply.setAlarmOk(rset.getString("alarm_ok"));
				
				replyList.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return replyList;
	}

	public int deleteReply(Connection conn, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from reply where reply_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Reply selectReply(Connection conn, int replyNo) {
		Reply reply = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from reply where reply_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reply = new Reply();
				reply.setReplyNo(replyNo);
				reply.setDiaryNo(rset.getInt("diary_no"));
				reply.setReplyContent(rset.getString("reply_content"));
				reply.setReplyWriter(rset.getString("reply_writer"));
				reply.setReplyDate(rset.getDate("reply_date"));
				reply.setLikeCount(rset.getInt("like_count"));
				reply.setHateCount(rset.getInt("hate_count"));
				reply.setAlarmOk(rset.getString("alarm_ok"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return reply;
	}

	public int updateReply(Connection conn, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update reply set reply_content = ? where reply_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getReplyNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Reply> replyAlarmRing(Connection conn, String diaryWriter) {
		ArrayList<Reply> alarmList = new ArrayList<Reply>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT REPLY_CONTENT, REPLY_WRITER, REPLY_DATE, DIARY_NO FROM REPLY JOIN DIARY USING(DIARY_NO) WHERE DIARY_WRITER = ? AND ALARM_OK = 'Y' order by diary_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryWriter);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply reply = new Reply();
				reply.setReplyContent(rset.getString("reply_content"));
				reply.setReplyWriter(rset.getString("reply_writer"));
				reply.setReplyDate(rset.getDate("reply_date"));
				reply.setDiaryNo(rset.getInt("diary_no"));				
				alarmList.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return alarmList;
	}

	public int replyAlarmCheck(Connection conn, String writer) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update (select * from reply join diary using(diary_no)) set alarm_ok = 'N' where diary_writer = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, writer);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertReply(Connection conn, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into reply values(reply_seq.nextval,?,?,?,default,default,default,default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reply.getDiaryNo());
			pstmt.setString(2, reply.getReplyContent());
			pstmt.setString(3, reply.getReplyWriter());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertLike(Connection conn, String counter, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into likecheck values(?,?,1)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			pstmt.setString(2, counter);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertHate(Connection conn, String counter, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into hatecheck values(?,?,1)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			pstmt.setString(2, counter);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int checkLike(Connection conn, String counter, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from likecheck where like_counter = ? and reply_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, counter);
			pstmt.setInt(2, replyNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	public int checkHate(Connection conn, String counter, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from hatecheck where hate_counter = ? and reply_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, counter);
			pstmt.setInt(2, replyNo);
			rset = pstmt.executeQuery();			
			if(rset.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}	

	public int updateLikeCount(Connection conn, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update reply set like_count = like_count + 1 where reply_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return result;
	}
	
	public int updateHateCount(Connection conn, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update reply set hate_count = hate_count + 1 where reply_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return result;
	}

}
