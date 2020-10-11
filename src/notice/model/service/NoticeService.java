package notice.model.service;

import static common.JDBCTemp.*;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
	public NoticeService() {}
	
	NoticeDao ndao = new NoticeDao();
	
	// 페이징
	public ArrayList<Notice> selectList(int currentPage, int limit){
		Connection conn = getConnection();
		ArrayList<Notice> list = ndao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}
	
	// 출력될 게시물 수
	public int getListCount() {
		Connection conn = getConnection();
		int listCount = ndao.getListCount(conn);
		close(conn);
		return listCount;
	}
	

	// 관리자 및 사용자의 공지사항 상세보기
	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		Notice notice = ndao.selectOne(conn, noticeNo);
		close(conn);
		return notice;
	}

	// 관리자용 공지사항 목록보기
	public ArrayList<Notice> selectAll() {
		Connection conn = getConnection();
		ArrayList<Notice> list = ndao.selectList(conn);
		close(conn);
		return list;
	}

	// 관리자용 공지사항 등록
	public int insertNotice(Notice notice) {
		Connection conn = getConnection();
		int result = ndao.insertNotice(conn, notice);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 관리자용 공지사항 삭제
	public int deleteNotice(int noticeno) {
		Connection conn = getConnection();
		int result = ndao.deleteNotice(conn, noticeno);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 관리자용 공지사항 수정
	public int updateNotice(Notice notice) {
		Connection conn = getConnection();
		int result = ndao.updateNotice(conn, notice);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	
	

	
}
