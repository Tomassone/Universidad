/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.96
 * Generated at: 2024-12-06 22:13:10 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Beans.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(7);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("Beans");
    _jspx_imports_packages.add("java.util");
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

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("   <head>\r\n");
      out.write("      <title>Index</title>\r\n");
      out.write("		<link type=\"text/css\" href=\"styles/default.css\" rel=\"stylesheet\"></link>\r\n");
      out.write("		<script type=\"text/javascript\" src=\"scripts/utils.js\"></script>\r\n");
      out.write("		<script type=\"text/javascript\" src=\"scripts/split.js\"></script>\r\n");
      out.write("		\r\n");
      out.write("   </head>\r\n");
      out.write("<body> \r\n");
      out.write("<h2>Inserisci valori nelle due matrici</h2>\r\n");
      out.write("\r\n");
      out.write("<h4>Prima matrice A:</h4>\r\n");
      out.write(" 	");
  //COSì ADMIN PUò SCEGLIERE DI QUALE GRUPPO FARE COSA
 	   for(int i=0; i<2; i++){
 		   for(int j=0; j<2; j++){
	
      out.write("\r\n");
      out.write("     <input type=\"number\" id=\"valueA");
      out.print(i);
      out.print(j);
      out.write("\" style=\"width: 40px;\" onchange=\"invio()\">\r\n");
      out.write("     \r\n");
 } 
      out.write(" \r\n");
      out.write("<br>\r\n");
      out.write("     \r\n");
} 
      out.write("\r\n");
      out.write("\r\n");
      out.write("<h4>Seconda matrice B:</h4>\r\n");
      out.write(" 	");
  //COSì ADMIN PUò SCEGLIERE DI QUALE GRUPPO FARE COSA
 	   for(int i=0; i<2; i++){
 		   for(int j=0; j<2; j++){
	
      out.write("\r\n");
      out.write("     <input type=\"number\" id=\"valueB");
      out.print(i);
      out.print(j);
      out.write("\" style=\"width: 40px;\" onchange=\"invio()\">\r\n");
      out.write("     \r\n");
 } 
      out.write(" \r\n");
      out.write("<br>\r\n");
      out.write("     \r\n");
} 
      out.write("\r\n");
      out.write("            <br>\r\n");
      out.write("   			<button style=\"display:none;\" id=\"bt1\" name=\"seq\" onclick=\"sequenz()\"/>Sequenziale</button>\r\n");
      out.write("			<button style=\"display:none;\" id=\"bt2\" name=\"parallelo\" onclick=\"paral()\"/>Parallelo</button>\r\n");
      out.write("   \r\n");
      out.write("   <div id=\"result\"></div><br><br>\r\n");
      out.write("   \r\n");
      out.write("  <h4>Admin inserisci le tue credenziali:</h4>\r\n");
      out.write(" <form action=\"servletAdmin\" method=\"post\">\r\n");
      out.write(" <label>Username: </label>\r\n");
      out.write(" <input type=\"text\" name=\"user\" ><br>\r\n");
      out.write(" <label>Password: </label>\r\n");
      out.write(" <input type=\"password\" name=\"pw\" ><br>\r\n");
      out.write(" <button type=\"submit\">Login</button>\r\n");
      out.write(" </form>\r\n");
      out.write(" \r\n");
      out.write("  ");
 
  HttpSession sessn = request.getSession();
  String user = (String)sessn.getAttribute("user");
  
  if(user!=null){
  Long conta = (Long) this.getServletContext().getAttribute("numoper");
  List<HttpSession> activeSessions = (List<HttpSession>) this.getServletContext().getAttribute("activeSession");
   
  if(conta!=null && activeSessions!=null) {
    if(conta>1 && activeSessions.size()>1){
 
  
      out.write("\r\n");
      out.write("  <h3>Stato delle Sessioni:</h3>\r\n");
      out.write("      <p>Numero di sessioni attive: ");
      out.print( activeSessions.size() );
      out.write("</p>\r\n");
      out.write("       <p>Operazioni effettuate nell'ultima ora: ");
      out.print( conta );
      out.write("</p>\r\n");
      out.write("     <form action=\"servletAdmin\" method=\"post\">\r\n");
      out.write("     <input type=\"submit\" id=\"termina\" name=\"term\" value=\"Forza Terminazione\">\r\n");
      out.write("    </form>\r\n");
      out.write("     ");

    }
     } }
      out.write("\r\n");
      out.write("   </body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
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
