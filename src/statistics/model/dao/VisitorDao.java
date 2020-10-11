package statistics.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class VisitorDao {

	public VisitorDao() {}

	public int loginHistory(Connection conn, String id, String pwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = " INSERT INTO VISITOR VALUES(?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2, pwd);
		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int loginCount(Connection conn) {
		int logincount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = " select count(accessdate) from visitor";
		
		try {
			stmt = conn.createStatement();
			rset= stmt.executeQuery(query);
			if(rset.next()) {
				logincount =rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return logincount;
	}
	
	
	

}
