package tw.org.iii;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Get_1204
 */
@WebServlet("/Get_1204")
public class Get_1204 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		//0. prepare
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		if(x==null) x="0";
		if(y==null) y = "0";
		
		//1. Model
		calculate add = new calculate();
		String res = add.cal(x,y);
		System.out.println(x +" +" +y +"=" + res);
		
		//2. Forward => View 
			request.setAttribute("x", x);
			request.setAttribute("y", y);
			request.setAttribute("res", res);
			request.getRequestDispatcher("Get_1204_2").forward(request, response);		
	}


}
