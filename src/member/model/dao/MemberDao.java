package member.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDao {

	public MemberDao() {
	}

	public Member loginCheck(Connection conn, String id, String pwd) {
		// 로그인
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from member where id = ? and pwd =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();

				member.setId(id);
				member.setPwd(pwd);
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));
				member.setResetpass(rset.getString("resetpass"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return member;
	}

	public int insertMember(Connection conn, Member member) {
		// 회원가입
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into member values(?, ?, ?, DEFAULT, ?, DEFAULT,SYSDATE,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getnName());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 아이디 확인
	public int selectCheckId(Connection conn, String id) {
		int idcount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(id) from member where id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				idcount = rset.getInt(1);
				// System.out.println("idcount : " + idcount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return idcount;
	}

	// 닉네임 확인
	public int selectChecknName(Connection conn, String nname) {
		int nnamecount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(nname) from member where nname = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nname);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				nnamecount = rset.getInt(1);
				// System.out.println("idcount : " + nnamecount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return nnamecount;
	}

	// 이메일 확인
	/*
	 * public String getEmail(String email) { PreparedStatement pstmt = null;
	 * ResultSet rset = null;
	 * 
	 * String query = "SELECT userEmail FROM member WHERE email = ?";
	 * 
	 * try {
	 * 
	 * pstmt.setString(1, email);
	 * 
	 * rset = pstmt.executeQuery();
	 * 
	 * while (rset.next()) {
	 * 
	 * return rset.getString(1); // 이메일 주소 반환
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return null;
	 * 
	 * }
	 */

	public Member selectMember(Connection conn, String id) {
		// 마이페이지
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from member where id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();

				member.setId(id);
				member.setPwd(rset.getString("pwd"));
				member.setEmail(rset.getString("email"));
				member.setEmailauth(rset.getString("emailauth"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));
				member.setResetpass(rset.getString("resetpass"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return member;
	}

	public int deleteMember(Connection conn, String id) {
		// 회원탈퇴
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from member where id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateMember(Connection conn, Member member) {
		// 수정페이지
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update member set pwd = ?, email =? where id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 현재 비밀번호 체크
	public Member selectPwd(Connection conn, String pwd) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from member where pwd = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();

				member.setId(rset.getString("id"));
				member.setPwd(pwd);
				member.setEmail(rset.getString("email"));
				member.setEmailauth(rset.getString("emailauth"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return member;
	}

	// 이메일 체크
	public int selectEmail(Connection conn, String email) {
		int emailcount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(email) from member where email = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				emailcount = rset.getInt(1);
				System.out.println("emailcount : " + emailcount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return emailcount;
	}

	// 비밀번호찾기
	public int updatePwd(Connection conn, String pwd, String id) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update member set pwd = ? where id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 인증번호 체크
	public int selectResetpass(Connection conn, String Resetpass) {
		int resetpasscount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(resetpass) from member where resetpass = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Resetpass);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				resetpasscount = rset.getInt(1);
				System.out.println("resetpasscount : " + resetpasscount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return resetpasscount;
	}

	// 관리자용

	// 관리자용 전체 회원 정보조회
	public ArrayList<Member> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, id, pwd, email, nname, "
				+ "enroll_date, lastmodified, dc_count, login_limit, status "
				+ "from (select  * from member order by enroll_date )) " + "where rnum>=? and rnum<=?";

		// rnum 값 계산하기
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setPwd(rset.getString("pwd"));
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));

				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	// 휴면회원인 회원 목록조회
	public ArrayList<Member> selectDorList(Connection conn) {
		ArrayList<Member> list = new ArrayList<Member>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from member where status=4";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setPwd(rset.getString("pwd"));
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	// 총 목록 갯수
	public int memberGetListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from member";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	// 블랙리스트 조회용
	public ArrayList<Member> selectBlackList(Connection conn, int currentPage, int limit) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, id, pwd, email, nname, "
				+ "enroll_date, lastmodified, dc_count, login_limit, status "
				+ "from (select  * from member where  login_limit = 'Y' order by enroll_date )) " 
				+ "where rnum>=? and rnum<=?";

		// rnum 값 계산하기
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);		
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
		
			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setPwd(rset.getString("pwd"));
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 신고 카운트 올리기
	public int updateDcCount(Connection conn,String receiver) {
		int dccount = 0;
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "update member set dc_count = dc_count+1 where nname = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, receiver);
			result = pstmt.executeUpdate();
			
			if (result > 0) { // 신고 건수 1 올리기 성공하면 신고건수를 리턴한다.
				String query2 = "select dc_count from member where nname =?";
				pstmt = conn.prepareStatement(query2);
				pstmt.setString(1, receiver);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					dccount = rset.getInt(1);
				}
			} else { // 신고건수 1올리기 실패하면 신고건수 =0을 리턴한다.
				dccount = 0;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dccount;
		
		
	}

	// 로그인 제한
	public int updateLoginLimit(Connection conn, String receiver) {

		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update member set login_limit='Y' where nname = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, receiver);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			close(pstmt);
		}
		return result;
		
	}

	// 회원등급변경
	public int updateMemberLevel(Connection conn, String status, String nname) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "UPDATE MEMBER SET STATUS=? WHERE NNAME = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			pstmt.setString(2, nname);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 회원등급 변경을 위한 닉네임 검색
	public Member selectStatusMember(Connection conn, String nname) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM MEMBER WHERE NNAME=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nname);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setStatus(rset.getString("status"));
				member.setnName(nname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public Member searchId(Connection conn, String email) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from member where email = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setId(rset.getString("id"));
				member.setPwd(rset.getString("pwd"));
				member.setEmail(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	// 휴면회원 전체 갯수 조회
	public int memberDorGetListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from member where status=4";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	// 관리자페이지에서 휴면 회원 아이디로 조회
	public ArrayList<Member> memberSearchId(Connection conn, String text1, int currentPage, int limit) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, id, pwd, email, nname, "
				+ "enroll_date, lastmodified, dc_count, login_limit, status "
				+ "from (select  * from member where id like ? and status=4 order by enroll_date )) " 
				+ "where rnum>=? and rnum<=?";

		// rnum 값 계산하기
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+text1+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));// 바꿔야 마지막 접속일로
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 관리자 페이지에서 휴면 회원의 닉네임으로 조회
	public ArrayList<Member> memberSearchNname(Connection conn, String text1,int currentPage, int limit) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, id, pwd, email, nname, "
				+ "enroll_date, lastmodified, dc_count, login_limit, status "
				+ "from (select  * from member where nname like ? and status=4 order by enroll_date )) " 
				+ "where rnum>=? and rnum<=?";

		// rnum 값 계산하기
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+text1+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));// 바꿔야 마지막 접속일로
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//휴면회원 해제 
	public int updateDorMember(Connection conn, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "update member set status=1 where id=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	//블랙리스트 해제
	public int blacklistUpn(Connection conn, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "update member set dc_count=0, login_limit='N' where id=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	//아이디로 조회했을 때 해당하는 목록 갯수
	public int memberGetListCountId(Connection conn, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from member where id like ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	//닉네임으로 조회했을 때 해당하는 목록 갯수
	public int memberGetListCountNn(Connection conn, String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from member where nname like ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	//가입일자로 조회했을 때 해당하는 목록 갯수
	public int memberGetListCountEd(Connection conn, Date begin, Date end) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from member where enroll_date between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, begin);
			pstmt.setDate(2, end);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	//아이디로 조회한 결과 중 현재 페이지에 나올 내용
	public ArrayList<Member> selectListid(Connection conn, String keyword, int currentPage, int limit) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, id, pwd, email, nname, "
				+ "enroll_date, lastmodified, dc_count, login_limit, status "
				+ "from (select  * from member where id like ? order by enroll_date )) " 
				+ "where rnum>=? and rnum<=?";

		// rnum 값 계산하기
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setPwd(rset.getString("pwd"));
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));

				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//닉네임으로 조회한 결과 중 현재 페이지에 나올 내용
	public ArrayList<Member> selectListNn(Connection conn, String keyword, int currentPage, int limit) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, id, pwd, email, nname, "
				+ "enroll_date, lastmodified, dc_count, login_limit, status "
				+ "from (select  * from member where nname like ? order by enroll_date )) " 
				+ "where rnum>=? and rnum<=?";

		// rnum 값 계산하기
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setPwd(rset.getString("pwd"));
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));

				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//가입일자로 조회한 결과 중 현재 페이지에 나올 내용
	public ArrayList<Member> selectListEd(Connection conn, Date begin, Date end, int currentPage, int limit) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, id, pwd, email, nname, enroll_date, "
				+ "lastmodified, dc_count, login_limit, status "
				+ "from (select  * from member where enroll_date between ? and ? order by enroll_date  )) "
				+ "where rnum>=? and rnum<=?";

		// rnum 값 계산하기
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, begin);
			pstmt.setDate(2, end);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setPwd(rset.getString("pwd"));
				member.setEmail(rset.getString("email"));
				member.setnName(rset.getString("nname"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setLastModified(rset.getDate("lastmodified"));
				member.setDcCount(rset.getInt("dc_count"));
				member.setLoginLimit(rset.getString("login_limit"));
				member.setStatus(rset.getString("status"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//휴면회원 - 아이디에 해당하는 갯수
	public int memberCountSearchId(Connection conn, String text1) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from member where id like ? and status=1 ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+text1+"%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	//휴면회원 - 닉네임에 해당하는 갯수
	public int memberCountSearchNname(Connection conn, String text1) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from member where nname like ? and status=1";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+text1+"%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	//블랙리스트 카운트
	public int memberBlGetListCountd(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from member where  login_limit = 'Y'";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

}
