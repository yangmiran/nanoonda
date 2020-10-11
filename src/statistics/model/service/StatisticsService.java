package statistics.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import statistics.model.dao.StatisticsDao;
import statistics.model.vo.Statistics;
import static common.JDBCTemp.*;

public class StatisticsService {
	private StatisticsDao sdao = new StatisticsDao();

	public StatisticsService() {

	}
	
	public int insertEnrollPath(Statistics statistics) {
		Connection conn = getConnection();
		int result = sdao.insertEnrollPath(conn, statistics);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	// 전체 목록 조회
	public ArrayList<Statistics> selectAll() {
		Connection conn = getConnection();
		ArrayList<Statistics> list = sdao.selectAll(conn);
		close(conn);
		return list;
	}

	// 가입경로
	public String[] insertEnrollPath() {
		Connection conn = getConnection();
		String[] str = sdao.insertEnrollPath(conn);
		close(conn);
		return str;
	}

	// 탈퇴이유
	public String[] insertReasonQuit() {
		Connection conn = getConnection();
		String[] str2 = sdao.insertReasonQuit(conn);
		close(conn);
		return str2;
	}
	
	public int insertStatistics(Statistics statistics) {
		Connection conn = getConnection();
		int result = sdao.insertStatistics(conn, statistics);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}

}
