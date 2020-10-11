package statistics.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import statistics.model.vo.Statistics;

public class StatisticsDao {

	public StatisticsDao() {

	}
	
	public int insertEnrollPath(Connection conn, Statistics statistics) {
		// 가입경로 등록
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO STATISTICS VALUES(null, ?, 0, DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, statistics.getEnrollpath());		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	// 전체 목록 조회
	public ArrayList<Statistics> selectAll(Connection conn) {
		ArrayList<Statistics> list = new ArrayList<Statistics>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM STATISTICS";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Statistics statistics = new Statistics();

				statistics.setReasonquit(rset.getString("reason_path"));
				statistics.setEnrollpath(rset.getString("enroll_path"));
				statistics.setVisitor(rset.getInt("visitor"));
				statistics.setVisitorDate(rset.getDate("visitor_date"));

				list.add(statistics);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	// 가입경로 그래프로
	public String[] insertEnrollPath(Connection conn) {
		String[] str = new String[5];
		Statement stmt = null;
		ResultSet rset = null;

		String query = "SELECT COUNT(*) FROM STATISTICS WHERE ENROLL_PATH =1 UNION all "
				+ "SELECT COUNT(*) FROM STATISTICS WHERE ENROLL_PATH =2 UNION all "
				+ "SELECT COUNT(*) FROM STATISTICS WHERE ENROLL_PATH =3 UNION all "
				+ "SELECT COUNT(*) FROM STATISTICS WHERE ENROLL_PATH =4 UNION all "
				+ "SELECT COUNT(*) FROM STATISTICS WHERE ENROLL_PATH =5 ";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			for (int i = 0; rset.next(); i++) {
				str[i] = rset.getString(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return str;

	}

	//탈퇴이유 그래프로
	public String[] insertReasonQuit(Connection conn) {
		String[] str2 = new String[4];
		Statement stmt = null;
		ResultSet rset = null;

		String query =
				"SELECT COUNT(*) FROM STATISTICS WHERE REASON_QUIT=1 UNION ALL "
				+ " SELECT COUNT(*) FROM STATISTICS WHERE REASON_QUIT=2 UNION ALL "
				+ " SELECT COUNT(*) FROM STATISTICS WHERE REASON_QUIT=3 UNION ALL "
				+ " SELECT COUNT(*) FROM STATISTICS WHERE REASON_QUIT=4";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			for (int i = 0; rset.next(); i++) {
				str2[i] = rset.getString(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return str2;

	}

	//탈퇴할때 탈퇴이유
		public int insertStatistics(Connection conn, Statistics statistics) {

			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = " INSERT INTO STATISTICS VALUES(?, null , 0, DEFAULT)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, statistics.getReasonquit());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
}
