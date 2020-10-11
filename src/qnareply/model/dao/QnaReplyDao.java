package qnareply.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import qnareply.model.vo.QnaReply;
import qna.model.vo.Qna;
public class QnaReplyDao {

	public QnaReplyDao() {

	}

	// 원글번호로 답글 선택
	public QnaReply selectReply(Connection conn, int qnaNum) {
		QnaReply qnareply = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from qna_reply where qna_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNum);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				qnareply = new QnaReply();
				qnareply.setQnaNo(qnaNum);
				qnareply.setNname(rset.getString("nname"));
				qnareply.setReplyTitle(rset.getString("reply_title"));
				qnareply.setReplyContent(rset.getString("reply_content"));
				qnareply.setReplyDate(rset.getDate("reply_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(qnareply);
		System.out.println(qnaNum);
		return qnareply;
	}

	// 출력될 게시물 수
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select from (select qna_no, qna_title,nname from qna union all select qna_no, reply_title, nname from qna_reply)order by qna_no desc, qna_title desc";

		try {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	// 전체 조회
	public ArrayList<QnaReply> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<QnaReply> list = new ArrayList<QnaReply>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM QNA_REPLY";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(1, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				QnaReply reply = new QnaReply();

				reply.setQnaNo(rset.getInt("qna_no"));
				reply.setReplyTitle(rset.getString("reply_title"));
				reply.setReplyContent(rset.getString("reply_content"));
				reply.setReplyDate(rset.getDate("reply_date"));
				reply.setNname(rset.getString("nname"));
	
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 관리자용 문의사항 답변 등록
	public int insertQnaReply(Connection conn, QnaReply qnareply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO QNA_REPLY VALUES(?, ?, ?, SYSDATE, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnareply.getQnaNo());
			pstmt.setString(2, qnareply.getReplyTitle());
			pstmt.setString(3, qnareply.getReplyContent());
			pstmt.setString(4, qnareply.getNname());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	}

	

