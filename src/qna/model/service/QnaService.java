package qna.model.service;

import static common.JDBCTemp.*;

import java.sql.Connection;
import java.util.ArrayList;
import member.model.vo.Member;
import qna.model.dao.QnaDao;
import qna.model.vo.Qna;

public class QnaService {

	private QnaDao qdao = new QnaDao();

	public QnaService() {

	}
	// 관리자용 문의사항 목록보기
	public ArrayList<Qna> selectAll() {
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.selectAll(conn);
		close(conn);
		return list;
	}
	// 사용자용 문의사항 등록
	public int insertQna(Qna qna) {
		Connection conn = getConnection();
		int result = qdao.insertQna(conn, qna);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 관리자 및 사용자의 문의사항 상세보기
	public Qna selectQna(int qnaNum) {
		Connection conn = getConnection();
		Qna qna = qdao.selectOne(conn, qnaNum);
		close(conn);
		return qna;

	}
	// 관리자용 문의사항 삭제
	public int deleteQna(int qnano) {
		Connection conn = getConnection();
		int result = qdao.deleteQna(conn, qnano);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	// 사용자용 문의사항 목록(닉네임으로 각자 목록만 출력)
	public ArrayList<Qna> selectSearchNname(String keyword) {
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.selectSearchNname(conn, keyword);
		close(conn);
		return list;
	}

	
	//ajax 사용한  문의사항 5개 출력 메인에
	public ArrayList<Qna> qnaAlarmList() {
		Connection conn = getConnection();
		ArrayList<Qna> list = qdao.qnaAlarmList(conn);
		close(conn);
		return list;
	}
	

	
}
