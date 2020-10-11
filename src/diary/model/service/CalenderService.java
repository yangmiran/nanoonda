package diary.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import diary.model.dao.CalenderDao;
import diary.model.vo.Diary;

public class CalenderService {
	private CalenderDao ddao = new CalenderDao();
	
	public CalenderService() {}
	
	public ArrayList<Diary> selectCalender(String diaryWriter) {
		//캘린더 작성자로 받아서 구현
		Connection conn = getConnection();
		//System.out.println("service==="+diaryWriter);
		ArrayList<Diary> list = ddao.selectCalender(conn, diaryWriter);
		close(conn);
		return list;
	}
	
	public ArrayList<Diary> selectYesCalender(String diaryWriter) {
		//캘린더 작성자로 받아서 구현
		Connection conn = getConnection();
		//System.out.println("service==="+diaryWriter);
		ArrayList<Diary> list = ddao.selectYesCalender(conn, diaryWriter);
		close(conn);
		return list;
	}
}
