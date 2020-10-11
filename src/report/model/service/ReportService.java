package report.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import member.model.vo.Member;

import static common.JDBCTemp.*;

import report.model.dao.ReportDao;
import report.model.vo.Report;

public class ReportService {
	public ReportService() {
	}

	// 의존성 주입
	private ReportDao rdao = new ReportDao();

	
	// 전체 조회시 사용하는 총 목록 갯수 조회
	public int reportGetListCount() {
		Connection conn = getConnection();
		int listCount = rdao.reportGetListCount(conn);
		close(conn);
		return listCount;
	}

	// 목록 갯수에 맞는 리스트 조회
	public ArrayList<Report> reportSelectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Report> list = rdao.reportSelectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	// 검색한 닉네임에 해당하는 목록의 갯수 조회
	public int reportGetListCount(String keyword) {
		Connection conn = getConnection();
		int listCount = rdao.reportGetListCount(conn, keyword);
		close(conn);
		return listCount;
	}

	// 검색한 닉네임에 해당하는 목록 조회
	public ArrayList<Report> reportSearch(int currentPage, int limit, String keyword) {
		Connection conn = getConnection();
		ArrayList<Report> list = rdao.reportSelectList(conn, currentPage, limit, keyword);
		close(conn);
		return list;
	}
	
	//신고내용 상세보기
		public Report reportDetail(int reportno) {
			Connection conn = getConnection();
			Report report = rdao.reportDetail(conn, reportno);
			close(conn);
			return report;
		}
		
		//미처리 상태인 신고글 5개 출력
		public ArrayList<Report> reportTypeSelectList() {
			Connection conn = getConnection();
			ArrayList<Report> list = rdao.reportTypeSelectList(conn);
		    close(conn);
		    return list;
			}
		//마이페이지에 출력될 나의 신고페이지의 총 글 갯수
		public int reportMyGetListCount(String sender) {
			Connection conn = getConnection();
			int listCount = rdao.reportMyGetListCount(conn, sender);
			close(conn);
			return listCount;
		}
		//마이페이지에 출력될 나의 신고페이지 글 목록 조회
		public ArrayList<Report> selectReportMyList(int currentPage, int limit,String sender) {
			Connection conn = getConnection();
			ArrayList<Report> list = rdao.selectReportMyList(conn, currentPage, limit, sender);
		    close(conn);
		    return list;
		}

		//오픈 일기장 신고하기
		public int reportInsert(Report report) {
			Connection conn = getConnection();
			int result = rdao.reportInsert(conn,report);
			close(conn);
			return result;
		}
		//받은편지 신고하기
		public int reportInsert2(Report report) {
			Connection conn = getConnection();
			int result = rdao.reportInsert2(conn,report);
			close(conn);
			return result;
		}
		//오픈 일기장의 신고가 중복인지 체크
		public int reportCheck(String reportsender,int diaryno) {
			Connection conn = getConnection();
			int listcount = rdao.reportCheck(conn,reportsender,diaryno);
			close(conn);
			return listcount;
		}
		//우편함의 신고가 중복인지 체크
		public int reportCheck2(String reportsender, int pno) {
			Connection conn = getConnection();
			int listcount = rdao.reportCheck2(conn,reportsender,pno);
			close(conn);
			return listcount;
		}
		//선택된 신고글 삭제
		public int deleteReport(int reportno) {
			Connection conn = getConnection();
			int result = rdao.deleteReport(conn,reportno);
			if(result>0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}
		//마이페이지 신고글 수정하기 위해 값 불러오기
		public Report reportSelectOne(int rno) {
			Connection conn = getConnection();
			Report report = rdao.reportSelectOne(conn,rno);
			close(conn);
			return report;
		}
		//신고글 수정하기
		public int reportUpdate(int rno, String title, String content, String writer) {
			Connection conn = getConnection();
			int result = rdao.reportUpdate(conn,rno,title,content, writer);
			if(result>0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}
		//신고완료 처리하기
		public int reportComUpdate(int reportno) {
			Connection conn = getConnection();
			int result = rdao.reportComUpdate(conn,reportno);
			if(result>0) 
				commit(conn); 
			else rollback(conn);	
			close(conn);
			return result;
		}
		//신고 완료 확인 여부
		public int reportCheckStatus(int reportno) {
			Connection conn = getConnection();
			int count = rdao.reportCheckStatus(conn,reportno);
			close(conn);
			return count;
		}
		//처리 상태에 따른 목록 출력
		public ArrayList<Report> reportTypeSelectList(String type) {
			Connection conn = getConnection();
			ArrayList<Report> list = rdao.reportSelectList(conn, type);
			close(conn);
			return list;
		}
		//내가 받은 신고 건수
		public int countMyReport(String sender) {
			Connection conn = getConnection();
			int reportcount = rdao.countMyReport(conn,sender);
			return reportcount;
		}
		
		
	

}
