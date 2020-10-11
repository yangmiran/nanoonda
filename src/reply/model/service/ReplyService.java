package reply.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import reply.model.dao.ReplyDao;
import reply.model.vo.Reply;
import static common.JDBCTemp.*;

public class ReplyService {
	//DI
	private ReplyDao rdao = new ReplyDao();
	
	public ReplyService() {}

	public ArrayList<Reply> selectAll(int diaryNo) {
		Connection conn = getConnection();
		ArrayList<Reply> replyList = rdao.selectAll(conn, diaryNo);
		close(conn);
		return replyList;
	}

	public int deleteReply(int replyNo) {
		Connection conn = getConnection();
		int result = rdao.deleteReply(conn, replyNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public Reply selectReply(int replyNo) {
		Connection conn = getConnection();
		Reply reply = rdao.selectReply(conn, replyNo);
		close(conn);
		return reply;
	}

	public int updateReply(Reply reply) {
		Connection conn = getConnection();
		int result = rdao.updateReply(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Reply> replyAlarmRing(String diaryWriter) {
		Connection conn = getConnection();
		ArrayList<Reply> alarmList = rdao.replyAlarmRing(conn, diaryWriter);
		close(conn);
		return alarmList;
	}

	public int replyAlarmCheck(String writer) {
		Connection conn = getConnection();
		int result = rdao.replyAlarmCheck(conn, writer);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertReply(Reply reply) {
		Connection conn = getConnection();
		int result = rdao.insertReply(conn, reply);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertLike(String counter, int replyNo) {
		Connection conn = getConnection();
		int result = rdao.insertLike(conn, counter, replyNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int insertHate(String counter, int replyNo) {
		Connection conn = getConnection();
		int result = rdao.insertHate(conn, counter, replyNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int checkLike(String counter, int replyNo) {
		Connection conn = getConnection();
		int checkResult = rdao.checkLike(conn, counter, replyNo);
		close(conn);
		return checkResult;
	}
	
	public int checkHate(String counter, int replyNo) {
		Connection conn = getConnection();
		int checkResult = rdao.checkHate(conn, counter, replyNo);
		close(conn);
		return checkResult;
	}

	public void updateLikeCount(int replyNo) {
		Connection conn = getConnection();
		rdao.updateLikeCount(conn, replyNo);
		close(conn);		
	}

	public void updateHateCount(int replyNo) {
		Connection conn = getConnection();
		rdao.updateHateCount(conn, replyNo);
		close(conn);
	}
}
