package diary.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import diary.model.vo.Diary;

public class CalenderDao {
	
	public CalenderDao() {}
	
	public ArrayList<Diary> selectCalender(Connection conn, String diaryWriter) {
		//System.out.println(diaryWriter);
		//캘린더 작성자로 받아서 구현
		ArrayList<Diary> list = new ArrayList<Diary>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from diary where diary_writer = ? and open_ok = 'Y' and temp_ok = 'N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryWriter);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				//System.out.println("while=======");
				Diary diary = new Diary();
				diary.setDiaryNo(rset.getInt("diary_no"));
				diary.setDiaryTitle(rset.getString("diary_title"));
				diary.setDiaryContent(rset.getString("diary_content"));
				diary.setDiaryWriter(rset.getString("diary_writer"));
				diary.setDiaryDate(rset.getDate("diary_date"));
				diary.setReadCount(rset.getInt("read_count"));
				diary.setTempOk(rset.getString("temp_ok"));
				list.add(diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Diary> selectYesCalender(Connection conn, String diaryWriter) {
		//System.out.println(diaryWriter);
		//open_ok = 'Y' 이면 오픈다이어리
		//open_ok = 'N' 이면 마이다이어리
		ArrayList<Diary> list = new ArrayList<Diary>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from diary where diary_writer = ? and open_ok = 'N' and temp_ok = 'N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryWriter);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				//System.out.println("while=======");
				Diary opdiary = new Diary();
				opdiary.setDiaryNo(rset.getInt("diary_no"));
				opdiary.setDiaryTitle(rset.getString("diary_title"));
				opdiary.setDiaryContent(rset.getString("diary_content"));
				opdiary.setDiaryWriter(rset.getString("diary_writer"));
				opdiary.setDiaryDate(rset.getDate("diary_date"));
				opdiary.setReadCount(rset.getInt("read_count"));
				opdiary.setTempOk(rset.getString("temp_ok"));
				list.add(opdiary);
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
