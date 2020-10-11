package diary.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import diary.model.vo.Diary;

public class DiaryDao {
	public DiaryDao() {}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from diary where open_ok = 'Y' and temp_ok = 'N'";
		
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

	public ArrayList<Diary> selectOpen(Connection conn, int currentPage, int limit) {
		ArrayList<Diary> list = new ArrayList<Diary>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rownum rnum, diary_no, diary_title, diary_content, diary_writer, "
				+ "diary_date, read_count, diary_originfile, diary_renamefile, temp_ok, open_ok "
				+ "from(select * from diary where open_ok = 'Y' and temp_ok = 'N' order by diary_no desc)) "
				+ "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Diary diary = new Diary();
				diary.setDiaryNo(rset.getInt("diary_no"));
				diary.setDiaryTitle(rset.getString("diary_title"));
				diary.setDiaryContent(rset.getString("diary_content"));
				diary.setDiaryWriter(rset.getString("diary_writer"));
				diary.setDiaryDate(rset.getDate("diary_date"));
				diary.setDiaryOriginfile(rset.getString("diary_originfile"));
				diary.setDiaryRenamefile(rset.getString("diary_renamefile"));
				diary.setReadCount(rset.getInt("read_count"));
				diary.setTempOk(rset.getString("temp_ok"));
				diary.setOpenOk(rset.getString("open_ok"));
				list.add(diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertDiary(Connection conn, Diary diary) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into diary values(diary_seq.nextval,?,?,?,default,?,?,default,default,default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diary.getDiaryTitle());
			pstmt.setString(2, diary.getDiaryContent());
			pstmt.setString(3, diary.getDiaryWriter());
			pstmt.setString(4, diary.getDiaryOriginfile());
			pstmt.setString(5, diary.getDiaryRenamefile());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int addReadCount(Connection conn, int diaryNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update diary set read_count = read_count + 1 where diary_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, diaryNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return result;
	}

	public Diary selectDiary(Connection conn, int diaryNo) {
		Diary diary = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from diary where diary_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, diaryNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				diary = new Diary();
				diary.setDiaryNo(diaryNo);
				diary.setDiaryTitle(rset.getString("diary_title"));
				diary.setDiaryContent(rset.getString("diary_content"));
				diary.setDiaryWriter(rset.getString("diary_writer"));
				diary.setDiaryDate(rset.getDate("diary_date"));
				diary.setDiaryOriginfile(rset.getString("diary_originfile"));
				diary.setDiaryRenamefile(rset.getString("diary_renamefile"));
				diary.setReadCount(rset.getInt("read_count"));
				diary.setTempOk(rset.getString("temp_ok"));
				diary.setOpenOk(rset.getString("open_ok"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return diary;
	}

	public ArrayList<Diary> selectTemp(Connection conn, String diaryWriter, int currentPage, int limit) {		
		ArrayList<Diary> list = new ArrayList<Diary>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rownum rnum, diary_no, diary_title, diary_content, diary_writer, "
				+ "diary_date, diary_originfile, diary_renamefile, read_count, temp_ok, open_ok "
				+ "from(select * from diary where diary_writer = ? and temp_ok = 'Y' order by diary_no desc)) "
				+ "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryWriter);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Diary diary = new Diary();
				diary.setDiaryNo(rset.getInt("diary_no"));
				diary.setDiaryTitle(rset.getString("diary_title"));
				diary.setDiaryContent(rset.getString("diary_content"));
				diary.setDiaryWriter(rset.getString("diary_writer"));
				diary.setDiaryDate(rset.getDate("diary_date"));
				diary.setDiaryOriginfile(rset.getString("diary_originfile"));
				diary.setDiaryRenamefile(rset.getString("diary_renamefile"));
				diary.setReadCount(rset.getInt("read_count"));
				diary.setTempOk(rset.getString("temp_ok"));
				list.add(diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int deleteDiary(Connection conn, int diaryNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from diary where diary_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, diaryNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateDiary(Connection conn, Diary diary) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update diary set diary_title = ?, diary_content = ?, diary_originfile = ?, diary_renamefile = ? where diary_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diary.getDiaryTitle());
			pstmt.setString(2, diary.getDiaryContent());			
			pstmt.setString(3, diary.getDiaryOriginfile());
			pstmt.setString(4, diary.getDiaryRenamefile());
			pstmt.setInt(5, diary.getDiaryNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateTempDiary(Connection conn, Diary diary) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update diary set diary_title = ?, diary_content = ?, diary_date = sysdate, diary_originfile = ?, diary_renamefile = ?, temp_ok = 'N' where diary_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diary.getDiaryTitle());
			pstmt.setString(2, diary.getDiaryContent());
			pstmt.setString(3, diary.getDiaryOriginfile());
			pstmt.setString(4, diary.getDiaryRenamefile());
			pstmt.setInt(5, diary.getDiaryNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int tempSave(Connection conn, Diary diary) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into diary values(diary_seq.nextval,?,?,?,default,?,?,default,'Y','Y')";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diary.getDiaryTitle());
			pstmt.setString(2, diary.getDiaryContent());
			pstmt.setString(3, diary.getDiaryWriter());
			pstmt.setString(4, diary.getDiaryOriginfile());
			pstmt.setString(5, diary.getDiaryRenamefile());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Diary> searchTitle(Connection conn, String keyword) {
		ArrayList<Diary> list = new ArrayList<Diary>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from diary where diary_title like ? and open_ok = 'Y' and temp_ok = 'N' order by diary_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Diary diary = new Diary();
				diary.setDiaryNo(rset.getInt("diary_no"));
				diary.setDiaryTitle(rset.getString("diary_title"));
				diary.setDiaryContent(rset.getString("diary_content"));
				diary.setDiaryWriter(rset.getString("diary_writer"));
				diary.setDiaryDate(rset.getDate("diary_date"));
				diary.setDiaryOriginfile(rset.getString("diary_originfile"));
				diary.setDiaryRenamefile(rset.getString("diary_renamefile"));
				diary.setReadCount(rset.getInt("read_count"));
				diary.setTempOk(rset.getString("temp_ok"));
				diary.setOpenOk(rset.getString("open_ok"));
				list.add(diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getMyListCount(Connection conn, String diaryWriter) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from diary where diary_writer = ? and open_ok = 'N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryWriter);
			rset = pstmt.executeQuery();
			if(rset.next()) {
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

	public ArrayList<Diary> selectMine(Connection conn, String diaryWriter, int currentPage, int limit) {
		ArrayList<Diary> list = new ArrayList<Diary>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rownum rnum, diary_no, diary_title, diary_content, diary_writer, "
				+ "diary_date, diary_originfile, diary_renamefile, read_count, temp_ok, open_ok "
				+ "from(select * from diary where diary_writer = ? and open_ok = 'N' order by diary_no desc)) "
				+ "where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryWriter);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Diary diary = new Diary();
				diary.setDiaryNo(rset.getInt("diary_no"));
				diary.setDiaryTitle(rset.getString("diary_title"));
				diary.setDiaryContent(rset.getString("diary_content"));
				diary.setDiaryWriter(rset.getString("diary_writer"));
				diary.setDiaryDate(rset.getDate("diary_date"));
				diary.setDiaryOriginfile(rset.getString("diary_originfile"));
				diary.setDiaryRenamefile(rset.getString("diary_renamefile"));
				diary.setReadCount(rset.getInt("read_count"));
				diary.setTempOk(rset.getString("temp_ok"));
				diary.setOpenOk(rset.getString("open_ok"));
				list.add(diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertMyDiary(Connection conn, Diary diary) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into diary values(diary_seq.nextval,?,?,?,default,?,?,default,default,'N')";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diary.getDiaryTitle());
			pstmt.setString(2, diary.getDiaryContent());
			pstmt.setString(3, diary.getDiaryWriter());
			pstmt.setString(4, diary.getDiaryOriginfile());
			pstmt.setString(5, diary.getDiaryRenamefile());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Diary> searchMydiary(Connection conn, String diaryWriter, String keyword) {
		ArrayList<Diary> list = new ArrayList<Diary>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from diary where diary_writer = ? and diary_title like ? and open_ok = 'N' order by diary_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryWriter);
			pstmt.setString(2, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Diary diary = new Diary();
				diary.setDiaryNo(rset.getInt("diary_no"));
				diary.setDiaryTitle(rset.getString("diary_title"));
				diary.setDiaryContent(rset.getString("diary_content"));
				diary.setDiaryWriter(rset.getString("diary_writer"));
				diary.setDiaryDate(rset.getDate("diary_date"));
				diary.setDiaryOriginfile(rset.getString("diary_originfile"));
				diary.setDiaryRenamefile(rset.getString("diary_renamefile"));
				diary.setReadCount(rset.getInt("read_count"));
				diary.setTempOk(rset.getString("temp_ok"));
				diary.setOpenOk(rset.getString("open_ok"));
				list.add(diary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getTempCount(Connection conn, String diaryWriter) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from diary where diary_writer = ? and temp_ok = 'Y'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, diaryWriter);
			rset = pstmt.executeQuery();
			if(rset.next()) {
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
}
