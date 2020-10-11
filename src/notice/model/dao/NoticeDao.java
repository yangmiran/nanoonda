package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import notice.model.vo.Notice;
import static common.JDBCTemp.close;

public class NoticeDao {

	public NoticeDao() {}

	// 공지사항 번호 선택하여 출력
	public Notice selectOne(Connection conn, int noticeNo) {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM NOTICE WHERE NOTICE_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNname(rset.getString("nname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return notice;
	}

	// 목록 출력
	public ArrayList<Notice> selectList(Connection conn) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM NOTICE ORDER BY NOTICE_DATE DESC";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Notice notice = new Notice();

				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNname(rset.getString("nname"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setOriginalFilepath(rset.getString("original_filepath"));
				notice.setRenameFilepath(rset.getString("rename_filepath"));
				
				list.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	// 등록
	public int insertNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO NOTICE VALUES(NOTICE_SEQ.NEXTVAL, ?,SYSDATE, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getOriginalFilepath());
			pstmt.setString(4, notice.getRenameFilepath());
			pstmt.setString(5, notice.getNname());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 삭제
	public int deleteNotice(Connection conn, int noticeno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "DELETE FROM NOTICE WHERE NOTICE_NO =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
				
	}

	// 수정
	public int updateNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE NOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=?, NOTICE_DATE=SYSDATE, ORIGINAL_FILEPATH=?, RENAME_FILEPATH=? WHERE NOTICE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getOriginalFilepath());
			pstmt.setString(4, notice.getRenameFilepath());
			pstmt.setInt(5, notice.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	// 닉네임별로 조회하여 리스트 출력
	public ArrayList<Notice> selectNnameList(Connection conn, String keyword){
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM NOTICE WHERE NNAME =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = new Notice();
				
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setOriginalFilepath(rset.getString("original_filepath"));
				notice.setRenameFilepath(rset.getString("rename_filepath"));
				notice.setNname(rset.getString("nname"));
				
				list.add(notice);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	// 닉네임별로 조회
	public ArrayList<String>selectNname(Connection conn){
		ArrayList<String> nname = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT DISTINCT NNAME FROM NOTICE";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				nname.add(rset.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return nname;
	}
	
	// 출력할 게시물 수
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) FROM NOTICE";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	
	// 페이징
	public ArrayList<Notice>selectList(Connection conn, int currentPage, int limit){
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * "
				+ "FROM(SELECT ROWNUM RNUM, NOTICE_NO, NOTICE_TITLE,NOTICE_DATE, NOTICE_CONTENT, ORIGINAL_FILEPATH, RENAME_FILEPATH, NNAME "
				+ "FROM(SELECT * FROM NOTICE ORDER BY NOTICE_DATE DESC, NOTICE_NO ASC)) "
				+ "WHERE RNUM >= ? AND RNUM <= ?";
		
		int startRow = (currentPage -1) * limit +1;
		int endRow = startRow + limit -1;
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setOriginalFilepath(rset.getString("original_filepath"));
				notice.setRenameFilepath(rset.getString("rename_filepath"));
				notice.setNname(rset.getString("nname"));
				list.add(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	
	
	
		

}
