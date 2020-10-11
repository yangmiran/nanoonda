package diary.model.service;

import static common.JDBCTemp.*;

import java.sql.Connection;
import java.util.ArrayList;

import diary.model.dao.DiaryDao;
import diary.model.vo.Diary;

public class DiaryService {
	//DI
	private DiaryDao ddao = new DiaryDao();
	public DiaryService() {}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = ddao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<Diary> selectOpen(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Diary> list = ddao.selectOpen(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public int insertDiary(Diary diary) {
		Connection conn = getConnection();
		int result = ddao.insertDiary(conn, diary);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public void addReadCount(int diaryNo) {
		Connection conn = getConnection();
		int result = ddao.addReadCount(conn, diaryNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	public Diary selectDiary(int diaryNo) {
		Connection conn = getConnection();
		Diary diary = ddao.selectDiary(conn, diaryNo);
		close(conn);
		return diary;
	}

	public ArrayList<Diary> selectTemp(String diaryWriter, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Diary> list = ddao.selectTemp(conn, diaryWriter, currentPage, limit);
		close(conn);
		return list;
	}	

	public int deleteDiary(int diaryNo) {
		Connection conn = getConnection();
		int result = ddao.deleteDiary(conn, diaryNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateDiary(Diary diary) {
		Connection conn = getConnection();
		int result = ddao.updateDiary(conn, diary);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updateTempDiary(Diary diary) {
		Connection conn = getConnection();
		int result = ddao.updateTempDiary(conn, diary);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int tempSave(Diary diary) {
		Connection conn = getConnection();
		int result = ddao.tempSave(conn, diary);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Diary> searchTitle(String keyword) {
		Connection conn = getConnection();
		ArrayList<Diary> list = ddao.searchTitle(conn, keyword);
		close(conn);
		return list;
	}

	public int getMyListCount(String diaryWriter) {
		Connection conn = getConnection();
		int listCount = ddao.getMyListCount(conn, diaryWriter);
		close(conn);
		return listCount;
	}

	public ArrayList<Diary> selectMine(String diaryWriter, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Diary> list = ddao.selectMine(conn, diaryWriter, currentPage, limit);
		close(conn);
		return list;
	}

	public int insertMyDiary(Diary diary) {
		Connection conn = getConnection();
		int result = ddao.insertMyDiary(conn, diary);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Diary> searchMydiary(String diaryWriter, String keyword) {
		Connection conn = getConnection();
		ArrayList<Diary> list = ddao.searchMydiary(conn, diaryWriter, keyword);
		close(conn);
		return list;
	}

	public int getTempCount(String diaryWriter) {
		Connection conn = getConnection();
		int listCount = ddao.getTempCount(conn, diaryWriter);
		close(conn);
		return listCount;
	}
	//뷰로 가기
	public Diary moveTo(int dno) {
		// TODO Auto-generated method stub
		return null;
	}
}
