package pro.model.service;

import static common.JDBCTemp.*;
import static common.JDBCTemp.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import pro.model.dao.ProDao;
import pro.model.vo.Pro;

public class ProService {

	public ProService() {
	}

	private ProDao pdao = new ProDao();

	// 전체 조회시 사용하는 총 목록 갯수 조회
	public int proGetListCount() {
		Connection conn = getConnection();
		int listCount = pdao.proGetListCount(conn);
		close(conn);
		return listCount;
	}

	// 목록 갯수에 맞는 리스트 조회
	public ArrayList<Pro> proSelectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Pro> list = pdao.proSelectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	// 관리자 권한으로 금지어 등록하기
	public int proInsert(Pro pro) {
		Connection conn = getConnection();
		int result = pdao.proInsert(conn, pro);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	 // title과 content를 금지어 목록과 비교하기 (오픈일기장, 우편함)
	   public boolean proCheck(String content, String title) {
	      Connection conn = getConnection();
	      
	      String[] titleList = title.split(" ");
	      String[] contentList = content.split(" ");
	      
	      ArrayList<String> list = pdao.proContent(conn);
	      boolean result = true;
	      
	      //title에 금지어 포함 되었는지 확인하기
	   
	      for (int i = 0; i < titleList.length; i++) { //title 단어 수 만큼돌리기
	         for (int j = 0; j < list.size(); j++) { // list 단어 수 만큼 돌리기
	            String word = list.get(j);
	            
	            if (titleList[i].contains(word)) { //금지어 포함
	               result = true;
	               break;
	            } else {//금지어 미포함이면 content 금지어 포함 되었는지 확인하기
	               for (int k = 0; k< contentList.length; k++) { // content 단어 수 만큼돌리기
	                  for (int l= 0; l < list.size(); l++) { // list 단어 수 만큼 돌리기
	                  
	                     String word2 = list.get(l);
	                     
	                     if (contentList[k].contains(word2)) {
	                        result = true;
	                        break;
	                     } else {
	                        result = false;
	                     }
	                  }
	               }
	            }
	         }
	      }      
	      return result;
	   }
	   //댓글용 금지어포함 확인
	   public boolean proCheck(String content) {
	      Connection conn = getConnection();
	      String[] contentList = content.split(" ");
	      
	      ArrayList<String> list = pdao.proContent(conn);
	      boolean result = true;
	      
	      //content 금지어 포함 되었는지 확인하기
	      for (int i = 0; i < contentList.length; i++) { // content 단어 수 만큼돌리기
	         for (int j = 0; j < list.size(); j++) { // list 단어 수 만큼 돌리기
	         
	            String word = list.get(j);
	            
	            if (contentList[i].contains(word)) { //금지어 포함
	               result = true;
	               break;
	            } else { //금지어 미포함
	               result = false;
	            }
	         }
	      }
	      return result;
	   }
	   //선택된 금지어 삭제
	public int deletePro(int prono) {
		Connection conn = getConnection();
		int result = pdao.deletePro(conn, prono);
		close(conn);
		return result;
	}
	//해당하는 금지어 검색
	public ArrayList<Pro> proSearch(String text, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Pro> list = pdao.proSearch(conn, text,currentPage, limit);
		close(conn);
		return list;
	}
	//금지어 등록 전 중복된 내용 확인
	public int dupleCheckPro(Pro pro) {
		Connection conn = getConnection();
		int count = pdao.proSearch(conn, pro);
		close(conn);
		return count;
	}
	//검색한 목록에 해당하는 갯수 조회
	public int proGetListCount(String text) {
		Connection conn = getConnection();
		int listCount = pdao.proGetListCount(conn, text);
		close(conn);
		return listCount;
	}
	   
	}