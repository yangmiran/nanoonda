/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.57
 * Generated at: 2020-08-16 21:43:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loginPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/views/member/../../views/common/meta.jsp", Long.valueOf(1597472488000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>NANOONDA_LOGIN</title>\r\n");
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
      out.write("<script type=\"text/javascript\" src=\"https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js\" charset=\"utf-8\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.11.3.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<!--mainWrap:s-->\r\n");
      out.write("\t<div id=\"mainWrap\">\r\n");
      out.write("\t\t<div class=\"main_bgWrap\">\r\n");
      out.write("\t\t\t<img src=\"/nnd/resources/images/main.jpg\" alt=\"배경이미지\" id=\"random_img\">\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!--main:s-->\r\n");
      out.write("\t\t<main id=\"loginWrap\">\r\n");
      out.write("\t\t\t<div class=\"bg_login_wrap\">\r\n");
      out.write("\t\t\t\t<img src=\"/nnd/resources/images/logofix.png\">\r\n");
      out.write("\t\t\t\t<h2>당신과 함께라서<br>봄이 왔어요</h2>\r\n");
      out.write("\t\t\t\t<form action=\"/nnd/login\" method=\"post\" class=\"loginform_wrap\">\r\n");
      out.write("\t\t\t\t\t<legend class=\"blind\">로그인입력폼</legend>\r\n");
      out.write("\t\t\t\t\t<div class=\"loginidWrap\">\r\n");
      out.write("\t\t\t\t\t\t<label for=\"loginNumber\" class=\"blind\">아이디</label> \r\n");
      out.write("\t\t\t\t\t\t<input id=\"loginNumber\" type=\"text\" class=\"loginNumber\" name=\"id\" value=\"\" placeholder=\"나눈다 아이디\" autofocus>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div class=\"loginPwWrap\">\r\n");
      out.write("\t\t\t\t\t\t<label for=\"loginPw\" class=\"blind\">비밀번호</label> \r\n");
      out.write("\t\t\t\t\t\t<input type=\"password\" id=\"loginPw\" class=\"loginPw\" name=\"pwd\" placeholder=\"비밀번호\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<p class=\"login_error\">아이디를 입력해주세요.</p>\r\n");
      out.write("\t\t\t\t\t<div class=\"submitBtn\">\r\n");
      out.write("\t\t\t\t\t\t<button type=\"submit\" class=\"btn_login\">로그인</button>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<!-- <div class=\"social_login\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"list\">\r\n");
      out.write("\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><div class=\"icon google\"></div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><div class=\"icon face\"></div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><div class=\"icon naver\"></div></li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li><div class=\"icon kaka\"></div></li>\r\n");
      out.write("\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div> -->\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<div class=\"loginSerch\">\r\n");
      out.write("\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"idpwdSerch.jsp#tabs-1\" target=\"_black\">아이디 찾기</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"idpwdSerch.jsp#tabs-2\" target=\"_black\">비밀번호 찾기</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"/nnd/views/email/emailok.jsp\">회원가입</a></li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</main>\r\n");
      out.write("\t\t<!--main:e-->\r\n");
      out.write("\t\t<!-- footer:s -->\r\n");
      out.write("\t\t<div id=\"main_footer\">\r\n");
      out.write("\t\t\t<div class=\"footer_list\">\r\n");
      out.write("\t\t\t\t<a class=\"link_info\" href=\"/nnd/index.jsp\" target=\"_blank\">NANOONDA</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"footer_copy\">\r\n");
      out.write("\t\t\t\t<small class=\"copyright\">COPYRIGHT 2020 NANOONDA CO. LTD. <br> ALL RIGHTS RESERVED.</small>\r\n");
      out.write("\t\t\t  </div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- footer:e -->\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--mainWrap:e-->\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\t//새로고침 할때마다 이미지 변경(메인 이미지)\r\n");
      out.write("\t\tfunction change_img(){\r\n");
      out.write("\t\t\tvar random_img=new Array();\r\n");
      out.write("\t\t\trandom_img[0]=\"/nnd/resources/images/main.jpg\";\r\n");
      out.write("\t\t\trandom_img[1]=\"/nnd/resources/images/main111.jpg\";\r\n");
      out.write("\t\t\tvar random_img_src=Math.floor(Math.random()*(random_img.length));\r\n");
      out.write("\t\t\tdocument.getElementById(\"random_img\").src=random_img[random_img_src];\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tchange_img();\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\t//로그인 alert\r\n");
      out.write("\t\t$(document).ready(function(){\r\n");
      out.write("\t\t\t$(\".btn_login\").click(function(){\r\n");
      out.write("\t\t\t\tvar text = $(\"#loginNumber\").val();\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\tif($(\"#loginNumber\").val()==\"\"){\r\n");
      out.write("\t\t\t\t\t$(\".login_error\").fadeIn(500);\r\n");
      out.write("\t\t\t\t\t$(\".login_error\").text(\"아이디를 입력해주세요\");\r\n");
      out.write("\t\t\t\t\t$(\"#loginNumber\").focus();\r\n");
      out.write("\t\t\t\t}else if ($(\"#loginPw\").val()==\"\"){\r\n");
      out.write("\t\t\t\t\t$(\".login_error\").fadeIn(500);\r\n");
      out.write("\t\t\t\t\t$(\".login_error\").text(\"비밀번호를 입력해주세요.\");\r\n");
      out.write("\t\t\t\t\t$(\"#loginPw\").focus();\r\n");
      out.write("\t\t\t\t}else {\r\n");
      out.write("\t\t\t\t\t$(\".login_error\").fadeIn(500);\r\n");
      out.write("\t\t\t\t\t$(\".login_error\").text(\"로그인되었습니다.\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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