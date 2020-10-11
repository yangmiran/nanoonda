package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.io.*; //FileReader

public class JDBCTemp {

	  public static Connection getConnection() {
		  Connection conn = null;
		  Properties prop = new Properties();
	
		  try {
			  String currentPath = JDBCTemp.class.getResource("./").getPath(); 
		
			  prop.load(new FileReader(currentPath + "jdbc.properties"));           
			  
			  String driver =  prop.getProperty("driver");
			  String url = prop.getProperty("url");
			  String user = prop.getProperty("user");
			  String passwd = prop.getProperty("passwd");
			  
			  Class.forName(driver);
			  conn = DriverManager.getConnection(url, user, passwd);
			  conn.setAutoCommit(false);
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  
		  return conn;
	   }

	  
	  public static void close(Connection conn) {
	     try {
	        if(conn != null && !conn.isClosed()) {
	           conn.close();
	        }
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	  }
	  
	  public static void close(Statement stmt) {
	     try {
	        if(stmt != null && !stmt.isClosed()) {
	           stmt.close();
	        }
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	  }
	  
	  public static void close(ResultSet rset) {
	     try {
	        if(rset != null && !rset.isClosed()) {
	           rset.close();
	        }
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	  }
	  
	  public static void commit(Connection conn) {
	     try {
	        if(conn != null && !conn.isClosed()) {
	           conn.commit();
	        }
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	  }
	  
	  public static void rollback(Connection conn) {
	     try {
	        if(conn != null && !conn.isClosed()) {
	           conn.rollback();
	        }
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	  }


}