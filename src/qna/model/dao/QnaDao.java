package qna.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import qna.model.vo.Qna;

public class QnaDao {

	public QnaDao() {

	}
	// 관리자용 문의사항 목록보기
	public ArrayList<Qna> selectAll(Connection conn) { 
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM QNA";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Qna qna = new Qna();

				qna.setQnaNo(rset.getInt("qna_no"));
				qna.setQnaTitle(rset.getString("qna_title"));
				System.out.println(qna.getQnaTitle());
				qna.setQnaContent(rset.getString("qna_content"));
				qna.setQnaDate(rset.getDate("qna_date"));
				qna.setQnaOriginalFilepath(rset.getString("qna_original_filepath"));
				qna.setQnaRenameFilepath(rset.getString("qna_rename_filepath"));
				qna.setNname(rset.getString("nname"));
				qna.setQnaAlarm(rset.getString("qna_alarm"));

				list.add(qna);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	// 사용자용 문의사항 등록
	public int insertQna(Connection conn, Qna qna) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO QNA VALUES(QNA_SEQ.NEXTVAL, ?,  ?, SYSDATE, ?, ?, ?, DEFAULT)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qna.getQnaTitle());
			pstmt.setString(2, qna.getQnaContent());
			pstmt.setString(3, qna.getQnaOriginalFilepath());
			pstmt.setString(4, qna.getQnaRenameFilepath());
			pstmt.setString(5, qna.getNname());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 관리자 및 사용자의 문의사항 상세보기
	public Qna selectOne(Connection conn, int qnaNum) {
		Qna qna = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM QNA WHERE QNA_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNum);

			rset = pstmt.executeQuery();
			if(rset.next()) {
			
				qna = new Qna();
				qna.setQnaNo(rset.getInt("qna_no"));
				qna.setQnaTitle(rset.getString("qna_title"));
				qna.setQnaContent(rset.getString("qna_content"));
				qna.setQnaDate(rset.getDate("qna_date"));
				qna.setQnaOriginalFilepath(rset.getString("qna_original_filepath"));
				qna.setQnaRenameFilepath(rset.getString("qna_rename_filepath"));
				qna.setNname(rset.getString("nname"));
				qna.setQnaAlarm(rset.getString("qna_alarm"));
				
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return qna;
	}
	// 관리자용 문의사항 삭제
	public int deleteQna(Connection conn, int qnano) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "DELETE FROM QNA WHERE QNA_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnano);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 유저용 문의사항 닉네임으로 검색하여 목록 추출
	public ArrayList<Qna> selectSearchNname(Connection conn, String keyword) {
		ArrayList<Qna> list = new ArrayList<Qna>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM QNA WHERE NNAME=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Qna qna = new Qna();
				qna.setQnaNo(rset.getInt("qna_no"));
				qna.setQnaTitle(rset.getString("qna_title"));
				qna.setQnaContent(rset.getString("qna_content"));
				qna.setQnaDate(rset.getDate("qna_date"));
				qna.setQnaOriginalFilepath(rset.getString("qna_original_filepath"));
				qna.setQnaRenameFilepath(rset.getString("qna_rename_filepath"));
				qna.setNname(rset.getString("nname"));
				qna.setQnaAlarm(rset.getString("qna_alarm"));
				list.add(qna);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	//ajax 사용한 문의사항 관리자 메인페이지에 5개 출력
			public ArrayList<Qna> qnaAlarmList(Connection conn) {
				ArrayList<Qna> list = new ArrayList<Qna>();
		       Statement stmt = null;
		        ResultSet rset =null;
		        
		        String query 
		        ="select * from (select rownum rnum, qna_no, qna_title, qna_content, qna_date, qna_original_filepath, qna_rename_filepath, nname, qna_alarm "
		        		+ "from (select * from qna where qna_alarm='N' order by qna_date desc)) where rnum >=1 and rnum <=3";
		        
		        try {
		            stmt = conn.createStatement();
		            rset =stmt.executeQuery(query);
		            while(rset.next()) {
						Qna qna = new Qna();
						
						qna.setNname(rset.getString("qna_no"));
						qna.setQnaTitle(rset.getString("qna_title"));
						qna.setQnaContent(rset.getString("qna_content"));
						qna.setQnaDate(rset.getDate("qna_date"));
						qna.setQnaOriginalFilepath(rset.getString("qna_original_filepath"));
						qna.setQnaRenameFilepath(rset.getString("qna_rename_filepath"));
						qna.setNname(rset.getString("nname"));
						qna.setQnaAlarm(rset.getString("qna_alarm"));
						
						list.add(qna);
					}

		        } catch (Exception e) {
		            e.printStackTrace();

		        }finally {

		            close(rset);
		            close(stmt);
		        }
		        //System.out.println(list);
		        return list;
		    }
	
}

	