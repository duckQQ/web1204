package tw.org.iii;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Brad16
 */
@WebServlet("/Brad16")
public class Brad16 extends HttpServlet {
	private PrintWriter out;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		outHTML();
	}
	
	private void outHTML()
	{
		out.println("<h1>Brad Big Company</h1><hr>");
		out.print("<table border='1' width='100%'>");
		out.print("<tr>");
		out.print("<th>Id.</th>");
		out.print("<th>Account</th>");
		out.print("<th>Password</th>");
		out.print("<th>RealName</th>");
		out.print("</tr>");
		
		out.print("</table>");
	}

}
