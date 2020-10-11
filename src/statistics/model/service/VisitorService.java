package statistics.model.service;

import java.sql.Connection;
import static common.JDBCTemp.*;
import statistics.model.dao.VisitorDao;

public class VisitorService {

	public VisitorService() {
	}

	private VisitorDao vdao = new VisitorDao();

	// 로그인 히스토리 내역 입력
	public int loginHistory(String id, String pwd) {
		Connection conn = getConnection();
		int result = vdao.loginHistory(conn, id, pwd);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	// 로그인 방문자수 조회
	public int loginCount() {
		Connection conn = getConnection();
		int logincount = vdao.loginCount(conn);
		return logincount;
	}

}
