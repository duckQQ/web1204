package tw.org.iii;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Brad15
 */
@WebServlet("/Brad15")
public class Brad15 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		testSQL();
	}
	private void testSQL(){
		try {
			//連接 java mysql jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			//建立jdbc與mysql的連線
			Connection conn = 
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/iii?user=root&password=root2003");
			Statement stmt = conn.createStatement();
			//輸入指令
			stmt.executeUpdate("INSERT INTO member (account,passwd,realname) VALUES ('duck','1234','Duck')");
			
			System.out.println("OK");
			
			
		} catch (Exception e) {
			System.out.println("driver error");
		}
	}

	

}
