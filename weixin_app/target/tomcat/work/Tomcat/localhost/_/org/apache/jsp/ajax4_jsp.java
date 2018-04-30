/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-01-22 17:24:29 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ajax4_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

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
      out.write("<title>Document</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<button id=\"btn\">load xml data</button>\r\n");
      out.write("\t<table class=\"table\">\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>id</td>\r\n");
      out.write("\t\t\t\t<td>name</td>\r\n");
      out.write("\t\t\t\t<td>age</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t\t<tbody>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t</tbody>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<script src=\"/static/js/ajax.js\"></script>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\t(function() {\r\n");
      out.write("\t\t\tvar btn = document.getElementById(\"btn\");\r\n");
      out.write("\t\t\tconsole.log(\"你好\");\r\n");
      out.write("\t\t\tvar xmlHttp = kaisheng.createXmlHttp();\r\n");
      out.write("\t\t\tconsole.log(xmlHttp);\r\n");
      out.write("\t\t\tbtn.onclick = function() {\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\txmlHttp.open(\"get\", \"/user.xml\");\r\n");
      out.write("\t\t\t\txmlHttp.onreadystatechange = function() {\r\n");
      out.write("\t\t\t\t\tif (xmlHttp.readyState == 4) {\r\n");
      out.write("\t\t\t\t\t\tif (xmlHttp.status == 200) {\r\n");
      out.write("\t\t\t\t\t\t\t//获得xml文档对象，并解析\r\n");
      out.write("\t\t\t\t\t\t\tvar data = xmlHttp.responseText;\r\n");
      out.write("\t\t\t\t\t\t\tvar xmlDoc = xmlHttp.responseXML;\r\n");
      out.write("\t\t\t\t\t\t\tconsole.log(xmlHttp);\r\n");
      out.write("\t\t\t\t\t\t\tvar userTags = xmlDoc.getElementsByTagName(\"user\");\r\n");
      out.write("\t\t\t\t\t\t\tfor(var i = 0; i < userTags.length; i++) {\r\n");
      out.write("\t\t\t\t\t\t\t\tvar userTag = userTags[i];\r\n");
      out.write("\t\t\t\t\t\t\t\tvar id = userTag.getAttribute(\"id\");\r\n");
      out.write("\t\t\t\t\t\t\t\tvar name = userTag.getElementsByTagName(\"name\")[0].childNodes[0].nodeValue;\r\n");
      out.write("\t\t\t\t\t\t\t\tvar age = userTag.getElementsByTagName(\"age\")[0].childNodes[0].nodeValue;\r\n");
      out.write("\t\t\t\t\t\t\t\t//console.log(\"id:\" + id + \",name:\" + name + \",age:\" + age);\r\n");
      out.write("\t\t\t\t\t\t\t\t//创建tr标签\r\n");
      out.write("\t\t\t\t\t\t\t\tvar tr = document.createElement(\"tr\");\r\n");
      out.write("\t\t\t\t\t\t\t\t//创建3个td标签\r\n");
      out.write("\t\t\t\t\t\t\t\tvar idTd = document.createElement(\"td\");\r\n");
      out.write("\t\t\t\t\t\t\t\tvar nameTd = document.createElement(\"td\");\r\n");
      out.write("\t\t\t\t\t\t\t\tvar ageTd = document.createElement(\"td\");\r\n");
      out.write("\t\t\t\t\t\t\t\t//创建文本子节点\r\n");
      out.write("\t\t\t\t\t\t\t\tvar idNode = document.createTextNode(id);\r\n");
      out.write("\t\t\t\t\t\t\t\tvar nameNode = document.createTextNode(name);\r\n");
      out.write("\t\t\t\t\t\t\t\tvar ageNode = document.createTextNode(age);\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tvar tbody = document.getElementsByTagName(\"tbody\")[0];\r\n");
      out.write("\t\t\t\t\t\t\t\t//拼装 td tr tbody\r\n");
      out.write("\t\t\t\t\t\t\t\tidTd.appendChild(idNode);//<td>1</td>\r\n");
      out.write("\t\t\t\t\t\t\t\tnameTd.appendChild(nameNode);//<td>jack</td>\r\n");
      out.write("\t\t\t\t\t\t\t\tageTd.appendChild(ageNode);//<td>23</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\ttr.appendChild(idTd);\r\n");
      out.write("\t\t\t\t\t\t\t\ttr.appendChild(nameTd);\r\n");
      out.write("\t\t\t\t\t\t\t\ttr.appendChild(ageTd);//<tr> <td>1</td> <td>jack</td> <td>23</td> </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\ttbody.appendChild(tr);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\talert(\"服务器开小差了...\")\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\txmlHttp.send();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t})();\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
