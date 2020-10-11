package report.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;

import static common.JDBCTemp.*;
import report.model.vo.Report;

public class ReportDao {
	 
	//전체 목록 갯수 조회하기
	public int reportGetListCount(Connection conn) {
		int listCount =0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from report";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	// 전체 목록 페이징해서 출력
	public ArrayList<Report> reportSelectList(Connection conn, int currentPage, int limit) {
		ArrayList<Report> list = new ArrayList<Report>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query=
				"select report_no, report_status, report_title, sender_nname, receiver_nname, report_reg_date, report_com_date "
				+ "from (select rownum rnum, report_no, report_status, report_title, sender_nname, receiver_nname, report_reg_date, report_com_date "
				+ "from (select  * from report order by report_no )) where rnum>=? and rnum<=?";
		
		//rnum 값 계산하기
		int startRow = (currentPage -1) * limit +1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Report report = new Report();
				report.setReportNo((rset.getInt("report_no")));
				report.setReportStatus(rset.getString("report_status"));
				report.setReportTiltle(rset.getString("report_title"));
				report.setsenderNname(rset.getString("sender_nname"));
				report.setreceiverNname(rset.getString("receiver_nname"));
				report.setReportRegDate(rset.getDate("report_reg_date"));
				report.setReportComDate(rset.getDate("report_com_date"));
					
				list.add(report);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	//검색한 닉네임에 해당하는 목록의 갯수 조회
	public int reportGetListCount(Connection conn, String keyword) {
		int listCount =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from report where receiver_nname like ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	//검색한 닉네임에 해당하는 목록 조회
	public ArrayList<Report> reportSelectList(Connection conn, int currentPage, int limit, String keyword) {
		ArrayList<Report> list = new ArrayList<Report>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="select report_no, report_status, report_title, sender_nname, receiver_nname, report_reg_date, report_com_date "
				+ "from (select rownum rnum, report_no, report_status, report_title, sender_nname, receiver_nname, report_reg_date, report_com_date"
				+ "from (select  * from report  order by report_no  )) where rnum>=? and rnum<=? and receiver_nname like ?";
		//rnum 값 계산하기
		int startRow = (currentPage -1) * limit +1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(1,startRow);
			pstmt.setInt(2, endRow);
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
						
				Report report = new Report();
				report.setReportNo((rset.getInt("report_no")));
				report.setReportStatus(rset.getString("report_status"));
				report.setReportTiltle(rset.getString("report_title"));
				report.setsenderNname(rset.getString("sender_nname"));
				report.setreceiverNname(rset.getString("receiver_nname"));
				report.setReportRegDate(rset.getDate("report_reg_date"));
				report.setReportComDate(rset.getDate("report_com_date"));
					
				list.add(report);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public Report reportDetail(Connection conn, int reportno) {
		Report report = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="select * from report where report_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reportno);		
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
						
				report = new Report();
				report.setReportNo(reportno);
				report.setReportStatus(rset.getString("report_status"));
				report.setReportTiltle(rset.getString("report_title"));
				report.setReportContent(rset.getString("report_content"));
				report.setsenderNname(rset.getString("sender_nname"));
				report.setreceiverNname(rset.getString("receiver_nname"));
				report.setReportRegDate(rset.getDate("report_reg_date"));
				report.setReportComDate(rset.getDate("report_com_date"));
				report.setReportType(rset.getString("report_type"));
				report.setDiaryNo(rset.getInt("diary_no"));
				report.setPostNo(rset.getInt("post_no"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return report;
	}
	//미처리 상태인 신고글 5개 출력
	public ArrayList<Report> reportTypeSelectList(Connection conn) {
		ArrayList<Report> list = new ArrayList<Report>();
        Statement stmt = null;
        ResultSet rset =null;
        
        String query 
        ="select * from (select rownum rnum, report_no, report_status, report_title, "
        		+ "report_content, sender_nname, receiver_nname, report_reg_date, "
        		+ "report_com_date, report_type, diary_no, post_no"
        + " from (select * from report where report_status=0 order by report_reg_date desc)) "
        + "where rnum >=1 and rnum <=5";
        
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery(query);
            while(rset.next()) {

                Report report = new Report();
                report.setReportNo(rset.getInt("report_no"));
                report.setReportStatus(rset.getString("report_status"));
				report.setReportTiltle(rset.getString("report_title"));
				report.setReportContent(rset.getString("report_content"));
				report.setsenderNname(rset.getString("sender_nname"));
				report.setreceiverNname(rset.getString("receiver_nname"));
				report.setReportRegDate(rset.getDate("report_reg_date"));
				report.setReportComDate(rset.getDate("report_com_date"));
				report.setReportType(rset.getString("report_type"));
				report.setDiaryNo(rset.getInt("diary_no"));
				report.setPostNo(rset.getInt("post_no"));
                
				list.add(report);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {

            close(rset);
            close(stmt);
        }

        return list;
    }
	//마이페이지에 출력될 나의 신고페이지의 총 글 갯수
	public int reportMyGetListCount(Connection conn, String sender) {
		int listCount =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from report where sender_nname= ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sender);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	//마이페이지에 출력될 나의 신고페이지 글 목록 조회
	public ArrayList<Report> selectReportMyList(Connection conn, int currentPage, int limit, String sender) {
		ArrayList<Report> list = new ArrayList<Report>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query
			="select * from (select rownum rnum, report_no, report_status, report_title, "
					+ "sender_nname, receiver_nname, report_reg_date, report_com_date "
					+ "from (select  * from report where sender_nname=? order by report_no  )) "
					+ "where rnum>=? and rnum<=?";
		//rnum 값 계산하기
		int startRow = (currentPage -1) * limit +1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(2,startRow);
			pstmt.setInt(3, endRow);
			pstmt.setString(1, sender);
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
						
				Report report = new Report();
				report.setReportNo((rset.getInt("report_no")));
				report.setReportStatus(rset.getString("report_status"));
				report.setReportTiltle(rset.getString("report_title"));
				report.setsenderNname(rset.getString("sender_nname"));
				report.setReportRegDate(rset.getDate("report_reg_date"));
				report.setReportComDate(rset.getDate("report_com_date"));
					
				list.add(report);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//오픈다이어리에서 신고하기 버튼 눌렀을 때 실행
	public int reportInsert(Connection conn, Report report) {
		int result =0;
		PreparedStatement pstmt = null;
		
		
		String query = "insert into report values(report_seq.nextval,0,?, ?,?,?,default,null,1,?,null)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, report.getReportTiltle());
			pstmt.setString(2, report.getReportContent());
			pstmt.setString(3, report.getsenderNname());
			pstmt.setString(4, report.getreceiverNname());
			pstmt.setInt(5, report.getDiaryNo());
			result= pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}
		
		return result;
	}

	//받은 우편에서 신고하기 버튼 눌렀을 때 실행
	public int reportInsert2(Connection conn, Report report) {
		int result =0;
		PreparedStatement pstmt = null;
		
		
		String query = "insert into report values(report_seq.nextval,0,?,?,?,?,default,null,2,null,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, report.getReportTiltle());
			pstmt.setString(2, report.getReportContent());
			pstmt.setString(3, report.getsenderNname());
			pstmt.setString(4, report.getreceiverNname());
			pstmt.setInt(5, report.getPostNo());
			result= pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}
		
		return result;
	}
	//오픈 일기장 신고글 중복 확인
	public int reportCheck(Connection conn, String reportsender,int diaryno) {
		int listcount =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query
			="select count(*) from report where sender_nname=? and diary_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reportsender);
			pstmt.setInt(2, diaryno);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				listcount = rset.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listcount;

	}
	//우편함 신고글 중복 확인
	public int reportCheck2(Connection conn, String reportsender, int pno) {
		int listcount =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query
			="select count(*) from report where sender_nname=? and post_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reportsender);
			pstmt.setInt(2, pno);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				listcount = rset.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listcount;
	}
	//선택된 신고글 삭제
	public int deleteReport(Connection conn, int reportno) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String query
			="delete from report where report_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reportno);
			result= pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	//신고글 수정하기 위해 글 불러오기
	public Report reportSelectOne(Connection conn, int rno) {
		Report report = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="select * from report where report_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rno);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				 report = new Report();
				 report.setReportNo(rno);
				 report.setReportStatus(rset.getString("report_status"));
				 report.setReportTiltle(rset.getString("report_title"));
				 report.setReportContent(rset.getString("report_content"));
				 report.setsenderNname(rset.getString("sender_nname"));
				 report.setreceiverNname(rset.getString("receiver_nname"));
				 report.setReportRegDate(rset.getDate("report_reg_date"));
				 report.setReportComDate(rset.getDate("report_com_date"));
				 report.setReportType(rset.getString("report_type"));
				 report.setDiaryNo(rset.getInt("diary_no"));
				 report.setPostNo(rset.getInt("post_no"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return report;
	}
	//신고글 수정하기
	public int reportUpdate(Connection conn, int rno, String title, String content, String writer) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String query ="update report set report_title=? ,report_content=? where report_no=? and  sender_nname=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, rno);
			pstmt.setString(4, writer);
			result= pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	//신고 완료 처리하기
	public int reportComUpdate(Connection conn, int reportno) {
		int result =0;
		PreparedStatement pstmt = null;
		
		String query ="update report set report_com_date = sysdate,  report_status='1' where report_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reportno);		
			result= pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	//신고 완료 확인 여부 ~0:미처리 상태인 갯수
	public int reportCheckStatus(Connection conn, int reportno) {
		int count =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="select count(*) from report where report_no=? and report_status=0 ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reportno);		
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count=rset.getInt(1);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
	//신고 처리에 따른 목록 출력
	public ArrayList<Report> reportSelectList(Connection conn, String type) {
		ArrayList<Report> list = new ArrayList<Report>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query="select * from report where report_status=?";
			
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, type);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Report report = new Report();
				report.setReportNo((rset.getInt("report_no")));
				report.setReportStatus(rset.getString("report_status"));
				report.setReportTiltle(rset.getString("report_title"));
				report.setsenderNname(rset.getString("sender_nname"));
				report.setreceiverNname(rset.getString("receiver_nname"));
				report.setReportRegDate(rset.getDate("report_reg_date"));
				report.setReportComDate(rset.getDate("report_com_date"));
					
				list.add(report);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	//나의 신고 건수 확인
	public int countMyReport(Connection conn, String sender) {
		int reportcount =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="select count(*) from report where receiver_nname=? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sender);		
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reportcount=rset.getInt(1);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return reportcount;
	}
	
	
	
}
