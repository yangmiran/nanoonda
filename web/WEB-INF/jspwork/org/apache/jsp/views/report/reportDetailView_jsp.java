/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.57
 * Generated at: 2020-08-18 01:56:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.report;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import report.model.vo.Report;
import member.model.vo.Member;

public final class reportDetailView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/views/report/../../views/common/meta.jsp", Long.valueOf(1597472488000L));
    _jspx_dependants.put("/views/report/../common/header.jsp", Long.valueOf(1597649064000L));
    _jspx_dependants.put("/views/report/../common/footer.jsp", Long.valueOf(1597297930000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("member.model.vo.Member");
    _jspx_imports_classes.add("report.model.vo.Report");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("  \r\n");

	Report report = (Report)request.getAttribute("report");


      out.write("    \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 마이페이지 신고게시판에서 제목누르면 나오는 상세보기    -->\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>NANOONDA MYPAGE</title>\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>NANOONDA</title>\r\n");
      out.write("<link href=\"/nnd/resources/css/basic.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"/nnd/resources/css/style.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"/nnd/resources/css/diarystyle.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"/nnd/resources/css/poststyle.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"/nnd/resources/css/adminstyle.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link href=\"/nnd/resources/css/themify-icons.css\" rel=\"stylesheet\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"/nnd/resources/js/jquery-3.5.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/nnd/resources/js/script.js\"></script>\r\n");
      out.write("<!--탭:js-->\r\n");
      out.write("<script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 이거 있어야 됨 관리자 사이드바 두줄 -->\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n");
      out.write("\r\n");
      out.write("</head>");
      out.write("\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write(" ");
      out.write('\r');
      out.write('\n');

   Member loginMember = (Member)session.getAttribute("loginMember");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>NANOONDA</title>\r\n");
      out.write("<script>\r\n");
      out.write("//상단 알림 오버시 컬러변경\r\n");
      out.write("$(document).ready(function(){\r\n");
      out.write("   $(\"#postbtn\").mouseover(function(){\r\n");
      out.write("      $(\"#postbtn img\").attr(\"src\", \"/nnd/resources/images/post_on.png\");\r\n");
      out.write("   });\r\n");
      out.write("   $(\"#postbtn\").mouseout(function(){\r\n");
      out.write("      $(\"#postbtn img\").attr(\"src\", \"/nnd/resources/images/post_off.png\");\r\n");
      out.write("   });\r\n");
      out.write("   \r\n");
      out.write("   $(\"#replybtn\").mouseover(function(){\r\n");
      out.write("      $(\"#replybtn img\").attr(\"src\", \"/nnd/resources/images/reply_on.png\");\r\n");
      out.write("   });\r\n");
      out.write("   $(\"#replybtn\").mouseout(function(){\r\n");
      out.write("      $(\"#replybtn img\").attr(\"src\", \"/nnd/resources/images/reply_off.png\");\r\n");
      out.write("   });\r\n");
      out.write("   \r\n");
      out.write("   //서브메뉴\r\n");
      out.write("   $(\"#headbar .headbar_menu #category ul li.sub\").mouseover(function(){\r\n");
      out.write("      $(this).css('color', '#9e5bd8');\r\n");
      out.write("      $(this).css('font-weight','bold');\r\n");
      out.write("      $('#headbar .headbar_menu #category .sublist').show();\r\n");
      out.write("   });\r\n");
      out.write("    $(\"#headbar .headbar_menu #category ul li.sub\").mouseout(function(){\r\n");
      out.write("      $(this).css('color', '#6f6f6f');\r\n");
      out.write("      $(this).css('font-weight','normal');\r\n");
      out.write("      $('#headbar .headbar_menu #category .sublist').hide();\r\n");
      out.write("   });\r\n");
      out.write("});\r\n");
      out.write("//스크롤시 메뉴 픽스\r\n");
      out.write("var ScrollTop = 0,\r\n");
      out.write("top = 15;\r\n");
      out.write("$(window).scroll(function(event) {\r\n");
      out.write("   var st = $(this).scrollTop();\r\n");
      out.write("   if (Math.abs(ScrollTop - st) <= top) return;\r\n");
      out.write("   if ((st > ScrollTop) && (ScrollTop > 0)) {\r\n");
      out.write("      $(\"#headbar .headbar_menu\").addClass(\"on\");\r\n");
      out.write("   } else {\r\n");
      out.write("      $(\"#headbar .headbar_menu\").removeClass(\"on\");\r\n");
      out.write("   }\r\n");
      out.write("   ScrollTop = st;\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("   <header id=\"headbar\" align=\"center\">\r\n");
      out.write("      <div id=\"hd_logo\" align=\"center\">\r\n");
      out.write("         <div class=\"alarm\">\r\n");
      out.write("          <span class=\"btn\" id=\"postbtn\"><a href=\"/nnd/pnotread\"><img src=\"/nnd/resources/images/post_off.png\"></a></span>\r\n");
      out.write("            <span class=\"btn\" id=\"replybtn\"><a href=\"/nnd/rring?writer=");
      out.print( loginMember.getnName() );
      out.write("\"><img src=\"/nnd/resources/images/reply_off.png\"></a></span>\r\n");
      out.write("         </div>\r\n");
      out.write("         <a href=\"/nnd/index.jsp\" class=\"ct_logo\"><img src = \"/nnd/resources/images/logofix.png\"></a>\r\n");
      out.write("         <div class=\"top_menu\">\r\n");
      out.write("            <ul>\r\n");
      out.write("               <li class=\"name\">");
      out.print( loginMember.getnName() );
      out.write(" 님</li>\r\n");
      out.write("               <li class=\"bar\"><a href=\"/nnd/myinfo?id=");
      out.print( loginMember.getId() );
      out.write("\">mypage</a></li>\r\n");
      out.write("               <li><a href=\"/nnd/logout\">logout</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("         </div>\r\n");
      out.write("      </div>\r\n");
      out.write("      \r\n");
      out.write("      <div class=\"headbar_menu\">\r\n");
      out.write("         <div class=\"menu_in clear\">\r\n");
      out.write("            <div id=\"category\">\r\n");
      out.write("               <ul>\r\n");
      out.write("                  <li><a href=\"/nnd/calenderlist\">HOME</a></li>\r\n");
      out.write("                  <li><a href=\"/nnd/dlist?page=1\">OPEN DIARY</a></li>\r\n");
      out.write("                  <li><a href=\"/nnd/mdlist?page=1\">MY DIARY</a></li>\r\n");
      out.write("                  <li class=\"sub\">\r\n");
      out.write("                     <a href=\"/nnd/views/post/postListView.jsp\">POST</a>\r\n");
      out.write("                     <div class=\"sublist\">\r\n");
      out.write("                        <ul>\r\n");
      out.write("                           <li><a href=\"/nnd/slist?page=1\">보낸편지함</a></li>\r\n");
      out.write("                           <li><a href=\"/nnd/rlist?page=1\">받은편지함</a></li>\r\n");
      out.write("                           <li><a href=\"/nnd/views/post/postWriteForm.jsp\">친구에게 보내기</a></li>\r\n");
      out.write("                           <li><a href=\"/nnd/views/post/someOneWriteForm.jsp\">누군가에게 보내기</a></li>\r\n");
      out.write("                           <li><a href=\"/nnd/ptlist?page=1\">임시저장함</a></li>\r\n");
      out.write("                        </ul>\r\n");
      out.write("                     </div>\r\n");
      out.write("                  </li>\r\n");
      out.write("               </ul>\r\n");
      out.write("               \r\n");
      out.write("            </div>\r\n");
      out.write("            <div id=\"board\">\r\n");
      out.write("               <ul>\r\n");
      out.write("                  <li><a href=\"/nnd/nlist\">Notice</a></li>\r\n");
      out.write("                  <li><a href=\"/nnd/views/qna/qnaWriterForm.jsp\">QnA</a></li>\r\n");
      out.write("               </ul>\r\n");
      out.write("            </div>\r\n");
      out.write("         </div>\r\n");
      out.write("      </div>\r\n");
      out.write("   </header>   \r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<h1 class=\"subtit\"><span>Report</span> 신고내역 상세보기</h1>\r\n");
      out.write("<div id=\"wrap\">\r\n");
      out.write("\t<div class=\"my_page\">\r\n");
      out.write("\t<div class=\"my_table\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table >\r\n");
      out.write("<tr><th>제목</th><td>");
      out.print( report.getReportTiltle() );
      out.write("</td></tr>\r\n");
      out.write("<tr><th>신고자</th><td>");
      out.print( report.getsenderNname() );
      out.write("</td></tr>\r\n");
      out.write("<tr><th>처리상태</th>\r\n");
      out.write("<td>\r\n");
if(Integer.parseInt(report.getReportStatus())==0){ 
      out.write("\r\n");
      out.write("미처리\r\n");
}else{ 
      out.write("\r\n");
      out.write("처리\r\n");
} 
      out.write("\r\n");
      out.write("</td></tr>\r\n");
      out.write("<tr><th>신고받은자</th><td>");
      out.print( report.getreceiverNname() );
      out.write("</td></tr>\r\n");
      out.write("<tr><th>내용</th><td>");
      out.print( report.getReportContent() );
      out.write("</td></tr>\r\n");
      out.write("<tr><th>신고날짜</th><td>");
      out.print( report.getReportRegDate() );
      out.write("</td></tr>\r\n");
      out.write("<tr><th>처리완료날짜</th>\r\n");
      out.write("<td>\r\n");
if(report.getReportComDate()==null){ 
      out.write("\r\n");
      out.write("&nbsp;\r\n");
}else{ 
      out.write('\r');
      out.write('\n');
      out.print( report.getReportComDate() );
      out.write('\r');
      out.write('\n');
} 
      out.write("\r\n");
      out.write("</td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("<!-- 상세보기에서 목록으로 돌아가기 -->\r\n");
      out.write("<div class=\"btn_in\">\r\n");
      out.write("\t<button onclick=\"javascript:history.go(-1); return false;\">목록</button>\r\n");
      out.write("\r\n");
      out.write(" <!-- 신고처리 완료,  -->\r\n");
      out.write(" ");
if(Integer.parseInt(report.getReportStatus())==1){//신고처리 상태가 처리완료된 상태이면 수정 불가 
      out.write("\r\n");
      out.write(" \r\n");
      out.write(" ");
}else{ 
      out.write("\r\n");
      out.write(" <input type=\"button\" id=\"reportupdate\" value=\"수정\" onclick=\"javascript:location.href='/nnd/reportup?rno=");
      out.print(report.getReportNo());
      out.write("'\">\r\n");
      out.write(" ");
} 
      out.write("\r\n");
      out.write("<input type=\"button\" id=\"reportdelete\" value=\"삭제\" onclick=\"javascript:location.href='/nnd/ronedelete?rno=");
      out.print(report.getReportNo());
      out.write("'\">\r\n");
      out.write("</div>\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("   </div>\r\n");
      out.write("   </div>  \r\n");
      out.write("  ");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>NANOONDA</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<footer>\r\n");
      out.write("\t<div class=\"footer_in\">\r\n");
      out.write("\t\t<div class=\"ft_top clear\">\r\n");
      out.write("\t\t\t<span class=\"ft_logo\"><img src=\"/nnd/resources/images/logo2.png\"></span>\r\n");
      out.write("\t\t\t<span class=\"sns\"><img src=\"/nnd/resources/images/sns.png\"></span>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<ul class=\"list clear\">\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<h1>CUSTOMER CENTER</h1>\r\n");
      out.write("\t\t\t\t<p class=\"tel\">1544-9970</p>\r\n");
      out.write("\t\t\t\t<p class=\"time\">\r\n");
      out.write("\t\t\t\t\t<span>10:00-17:00</span> <span>LUNCH TIME 11:30-13:00</span>\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<h1>MENU</h1>\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">HOME</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">OPEN DIARY</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">MY DIARY</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"/nnd/views/post/postListView.jsp\">POST</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">NOTICE</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">CONTACT</a></li>\r\n");
      out.write("\t\t\t\t</ul> <small> COPYRIGHT 2020 NANOONDA CO. LTD. <br> ALL RIGHTS RESERVED.\r\n");
      out.write("\t\t\t</small>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("</footer>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("         \r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
