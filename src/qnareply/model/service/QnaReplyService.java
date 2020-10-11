package qnareply.model.service;

import static common.JDBCTemp.*;

import java.sql.Connection;
import java.util.ArrayList;

import qnareply.model.dao.QnaReplyDao;
import qnareply.model.vo.QnaReply;

public class QnaReplyService {

	public QnaReplyService() {
	}

	public QnaReplyDao qrdao = new QnaReplyDao();

	public QnaReply selectReply(int qnaNum) {
		Connection conn = getConnection();
		QnaReply qnareply = qrdao.selectReply(conn, qnaNum);
		close(conn);
		return qnareply;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = qrdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<QnaReply> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<QnaReply> list = qrdao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	// 관리자용 문의사항 답변 등록
	public int insertQnaReply(QnaReply qnareply) {
		Connection conn = getConnection();
		int result = qrdao.insertQnaReply(conn, qnareply);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	
	

	
	
	
	
	

}