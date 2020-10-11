package member.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;

import static common.JDBCTemp.*;

public class MemberService {
	private MemberDao mdao = new MemberDao();

	public MemberService() {
	}

	// 로그인
	public Member loginCheck(String id, String pwd) {
		Connection conn = getConnection();
		Member member = mdao.loginCheck(conn, id, pwd);
		close(conn);
		return member;
	}

	// 회원가입
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = mdao.insertMember(conn, member);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 아이디 확인
	public int selectCheckId(String id) {
		Connection conn = getConnection();
		int idcount = mdao.selectCheckId(conn, id);
		close(conn);
		return idcount;
	}

	// 닉네임 확인
	public int selectChecknName(String nname) {
		Connection conn = getConnection();
		int idcount = mdao.selectChecknName(conn, nname);
		close(conn);
		return idcount;
	}

	// 마이페이지 정보 조회
	public Member selectMember(String id) {
		Connection conn = getConnection();
		Member member = mdao.selectMember(conn, id);
		close(conn);
		return member;
	}

	public int deleteMember(String id) {
		// 회원탈퇴
		Connection conn = getConnection();
		int result = mdao.deleteMember(conn, id);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateMember(Member member) {
		// 회원정보 수정
		Connection conn = getConnection();
		int result = mdao.updateMember(conn, member);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 현재 비밀번호 체크
	public Member selectPwd(String pwd) {
		Connection conn = getConnection();
		Member member = mdao.selectPwd(conn, pwd);
		close(conn);
		return member;
	}

	public int selectEmail(String email) {
		Connection conn = getConnection();
		int emailcount = mdao.selectEmail(conn, email);
		close(conn);
		return emailcount;
	}

	// 비밀번호찾기
	public int updatePwd(String pwd, String id) {
		Connection conn = getConnection();
		int result = mdao.updatePwd(conn, pwd, id);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	//회원가입 인증 체크
		public int selectResetpass(String resetpass) {
			Connection conn = getConnection();
			int resetpasscount = mdao.selectResetpass(conn, resetpass);
			close(conn);
			return resetpasscount;
		}
	
		public Member searchId(String email) {
			Connection conn = getConnection();
			Member member = mdao.searchId(conn, email);
			close(conn);
			return member;
		}
		
	
	
	
	// 관리자용 메소드

	// 관리자용 회원전체 조회
	public ArrayList<Member> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	

	// 휴면회원 조회하기
	public ArrayList<Member> selectDorList() {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectDorList(conn);
		close(conn);
		return list;
	}

	// 회원 총 목록
	public int memberGetListCount() {
		Connection conn = getConnection();
		int listcount = mdao.memberGetListCount(conn);
		close(conn);
		return listcount;
	}

	// 블랙리스트 조회용
	public ArrayList<Member> selectBlackList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectBlackList(conn,currentPage, limit);
		close(conn);
		return list;
	}

	// 신고 카운트 올리기
	public int updateDcCount( String receiver) {
		Connection conn = getConnection();
		int dccount = mdao.updateDcCount(conn, receiver);
		 if (dccount != 0) 
			 commit(conn); 
		 else 
			 rollback(conn);
		
		return dccount;

	}

	// 로그인 제한 하기
	public int updateLoginLimit(String receiver) {
		Connection conn = getConnection();
		int result = mdao.updateLoginLimit(conn, receiver);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		return result;
	}

	// 회원등급변경
		public int updateMemberLevel(String status, String nname) {
			Connection conn = getConnection();
			int result = mdao.updateMemberLevel(conn, status, nname);
			close(conn);
			return result;
		}

		// 회원등급 변경을 위한 닉네임 검색
		public Member selectStatusMember(String nname) {
			Connection conn = getConnection();
			Member member = mdao.selectStatusMember(conn, nname);
			close(conn);
			return member;
		}
		//휴면회원 전체 목록 갯수 조회하기
		public int memberDorGetListCount() {
			Connection conn = getConnection();
			int listcount = mdao.memberDorGetListCount(conn);
			close(conn);
			return listcount;
		}
		//관리자 페이지에서 아이디로 회원 조회
		public ArrayList<Member> memberSearchId(String text1,int currentPage, int limit) {
			Connection conn = getConnection();
			ArrayList<Member> list = mdao.memberSearchId(conn, text1,currentPage, limit);
			close(conn);
			return list;
		}
		//관리자 페이지에서 닉네임으로 회원 조회
		public ArrayList<Member> memberSearchNname(String text1,int currentPage,int limit) {
			Connection conn = getConnection();
			ArrayList<Member> list = mdao.memberSearchNname(conn, text1,currentPage, limit);
			close(conn);
			return list;
		}
		//휴면 계정 해제 
		public int updateDorMember(String id) {
			Connection conn = getConnection();
			int result = mdao.updateDorMember(conn, id);
			close(conn);
			return result;
		}
		//블랙리스트 업데이트
		public int blacklistUpn(String id) {
			Connection conn = getConnection();
			int result = mdao.blacklistUpn(conn, id);
			close(conn);
			return result;
		}
		//아이디로 검색했을 때 목록 갯수
		public int memberGetListCountId(String keyword) {
			Connection conn = getConnection();
			int listcount = mdao.memberGetListCountId(conn, keyword);
			close(conn);
			return listcount;
		}
		//닉네임으로 검색했을 떄 목록 갯수
		public int memberGetListCountNn(String keyword) {
			Connection conn = getConnection();
			int listcount = mdao.memberGetListCountNn(conn, keyword);
			close(conn);
			return listcount;
		}
		//가입일자로 검색했을 때 목록 갯수
		public int memberGetListCountEd(Date begin, Date end) {
			Connection conn = getConnection();
			int listcount = mdao.memberGetListCountEd(conn, begin, end);
			close(conn);
			return listcount;
		}
		//아이디로 조회한 결과 중 현재 페이지에 나올 내용
		public ArrayList<Member> selectListid(String keyword, int currentPage, int limit) {
			Connection conn = getConnection();
			ArrayList<Member> list = mdao.selectListid(conn, keyword,currentPage, limit);
			close(conn);
			return list;
		}
		//닉네임으로 조회한 결과 중 현재 페이지에 나올 내용
		public ArrayList<Member> selectListNn(String keyword, int currentPage, int limit) {
			Connection conn = getConnection();
			ArrayList<Member> list = mdao.selectListNn(conn, keyword,currentPage, limit);
			close(conn);
			return list;
		}
		//가입일자로 조회한 결과 중 현재 페이지에 나올 내용
		public ArrayList<Member> selectListEd(Date begin, Date end, int currentPage, int limit) {
			Connection conn = getConnection();
			ArrayList<Member> list = mdao.selectListEd(conn, begin, end,currentPage, limit);
			close(conn);
			return list;
		}
		//휴면회원 검색 -아이디에 해당하는 갯수
		public int memberCountSearchId(String text1) {
			Connection conn = getConnection();
			int listcount = mdao.memberCountSearchId(conn, text1);
			close(conn);
			return listcount;
		}
		//휴면회원 검색 -닉네임에 해당하는 갯수
		public int memberCountSearchNname(String text1) {
			Connection conn = getConnection();
			int listcount = mdao.memberCountSearchNname(conn, text1);
			close(conn);
			return listcount;
		}
		//블랙리스트 페이징
		public int memberBlGetListCount() {
			Connection conn = getConnection();
			int listcount = mdao.memberBlGetListCountd(conn);
			close(conn);
			return listcount;
		}
}
