package tw.org.iii;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Get_1204_2
 */
@WebServlet("/Get_1204_2")
public class Get_1204_2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//得到Get_1204的setAttribute的資料
		String x = (String)request.getAttribute("x");
		String y = (String)request.getAttribute("y");
		String res = (String)request.getAttribute("res");
		
		
		// Load view1.html
		String htmlString = loadView("view.html");
		//htmlString = String.format(htmlString, "10","3","13");
		//改成可以從網頁上面去取得參數
		htmlString = String.format(htmlString, x,y,res);
		out.println(htmlString);
	}
	//load view的方法，讀入html檔案
	private String loadView(String file){
		//從web.xml檔案 找到 <context-param> 中的網頁檔案
		String templatePath = 
				getServletContext().getInitParameter("template-path");
		//IO串流
		File tempFile = new File(templatePath, file);
		long len = tempFile.length();
		byte[] buf = new byte[(int)len];
		try{
			BufferedInputStream bin =
					new BufferedInputStream(
							new FileInputStream(tempFile));
			bin.read(buf);
			bin.close();
		}catch(Exception ee){
			
		}
		
		return new String(buf);
	}

}
