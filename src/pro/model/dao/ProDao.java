package pro.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pro.model.vo.Pro;


public class ProDao {
	public  ProDao() {}

	//전체 목록의 갯수 조회하기
	public int proGetListCount(Connection conn) {
		int listCount =0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from pro";
		
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
	//전체 조회하기
	public ArrayList<Pro> proSelectList(Connection conn, int currentPage, int limit) {
		ArrayList<Pro> list = new ArrayList<Pro>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query
			="select * from (select rownum rnum, pro_no, pro_content, admin_nname, pro_date "
					+ "from (select  * from pro  order by pro_no )) where rnum>=? and rnum<=?";
		
		//rnum 값 계산하기
		int startRow = (currentPage -1) * limit +1;
		int endRow = startRow + limit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Pro pro = new Pro();
				pro.setProNo(rset.getInt("pro_no"));
				pro.setProContent(rset.getString("pro_content"));
				pro.setAdminNname(rset.getString("admin_nname"));
				pro.setProDate(rset.getDate("pro_date"));
				
				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	//관리자 권한으로 금지어 등록하기
	public int proInsert(Connection conn, Pro pro) {
		int result =0;
		PreparedStatement pstmt = null;
				
		String query = "insert into pro values(pro_seq.nextval, ?,?,default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pro.getProContent());
			pstmt.setString(2, pro.getAdminNname());
			result= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	//금지어 담아가기
	public ArrayList<String> proContent(Connection conn) {
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query ="select pro_content from pro ";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				//Pro pro = new Pro();				
				//pro.setProContent(rset.getString("pro_content"));		
				list.add(rset.getString("pro_content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}
	//선택된 금지어 삭제 
	public int deletePro(Connection conn, int prono) {
		int result =0;
		PreparedStatement pstmt = null;
				
		String query = "delete from pro where pro_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, prono);			
			result= pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	//해당하는 금지어 조회
	public ArrayList<Pro> proSearch(Connection conn, String text, int currentPage, int limit) {
		ArrayList<Pro> list = new ArrayList<Pro>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rownum rnum, pro_no, pro_content, admin_nname, pro_date "
		+ "from (select  * from pro where pro_content like ? order by pro_no )) where rnum>=? and rnum<=?";
		
		//rnum 값 계산하기
		int startRow = (currentPage -1) * limit +1;
		int endRow = startRow + limit -1;
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+text+"%");
			pstmt.setInt(2,startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Pro pro = new Pro();				
				pro.setProNo(rset.getInt("pro_no"));
				pro.setProContent(rset.getString("pro_content"));
				pro.setAdminNname(rset.getString("admin_nname"));
				pro.setProDate(rset.getDate("pro_date"));
				
				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	
	//금지어 등록전 중복 체크
	public int proSearch(Connection conn, Pro pro) {
		int result =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from pro where pro_content=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pro.getProContent());			
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	//검색에 해당하는 text의 갯수 조회
	public int proGetListCount(Connection conn, String text) {
		int listcount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="select count(*) from pro where pro_content like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+text+"%");
			rset = pstmt.executeQuery();
			
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
	
}
