package tw.org.iii;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

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
	private Connection conn = null;
	@Override
	public void init() throws ServletException {
		super.init();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//建立連線資訊
			Properties prop = new Properties();
			prop.setProperty("user", "root");
			prop.setProperty("password", "root2003");
			//開始連線
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/iii", prop);
			
			//System.out.println("OK");
		}catch (Exception e)
		{
				System.out.println("XX");
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");
		String delid = request.getParameter("delid");
		if (type!=null && type.equals("add")){
			// insert into
			String account = request.getParameter("account"); 
			String passwd = request.getParameter("passwd"); 
			String realname = request.getParameter("realname"); 
			addData(account,passwd,realname);
		}else if(delid!=null){
			delData(delid);
		}else if(type!=null && type.equals("edit")){
			String updateid = request.getParameter("updateid"); 
			String account = request.getParameter("account"); 
			String passwd = request.getParameter("passwd"); 
			String realname = request.getParameter("realname"); 
			editData(updateid,account,passwd,realname);
		}
		
		
		outHTML(queryData());
	}
	//deldata方法
	private void delData(String id)
	{
		try {
			PreparedStatement pstmt = 
					conn.prepareStatement("Delete from member where id=?");
			pstmt.setString(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// addData方法
	private void addData(String account,String passwd,String realname)
	{
		try {
			PreparedStatement pstmt = 
					conn.prepareStatement(
							"insert into member(account, passwd, realname) values(?,?,?)");
			pstmt.setString(1, account);
			pstmt.setString(2, passwd);
			pstmt.setString(3, realname);
			pstmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	//修改資料
	private void editData(String updateid, String account,String passwd,String realname){
		try {
			PreparedStatement pstmt = 
					conn.prepareStatement(
							"update member set account=?,passwd=?,realname=? where id = ?");
			pstmt.setString(1, account);
			pstmt.setString(2, passwd);
			pstmt.setString(3, realname);
			pstmt.setString(4, updateid);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	
	//新增ArrayList方法
	private ArrayList<HashMap<String,String>> queryData(){
		ArrayList<HashMap<String,String>> data = new ArrayList<>();
		try {
			PreparedStatement pstmt = 
					conn.prepareStatement("SELECT * FROM member");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()){
				HashMap<String,String> row = new HashMap<>();
				row.put("id", rs.getString("id"));
				row.put("account", rs.getString("account"));
				row.put("passwd", rs.getString("passwd"));
				row.put("realname", rs.getString("realname"));
				data.add(row);
			}
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return data;
	}
	
	private void outHTML(ArrayList<HashMap<String,String>> data)
	{
		
		out.println("<h1>Brad Big Company</h1><hr>");
		out.print("<table border='1' width='100%'>");
		out.println("<a href='Duck17.html'>Add new</a>");
		out.print("<tr>");
		out.print("<th>Id.</th>");
		out.print("<th>Account</th>");
		out.print("<th>Password</th>");
		out.print("<th>RealName</th>");
		out.print("<th>Delete</th>");
		out.print("<th>Edit</th>");
		out.print("</tr>");
		
		for (HashMap<String,String> row : data){
			out.print("<tr>");
			out.print(String.format("<td>%s</td>",row.get("id")));
			out.print(String.format("<td>%s</td>",row.get("account")));
			out.print(String.format("<td>%s</td>",row.get("passwd")));
			out.print(String.format("<td>%s</td>",row.get("realname")));
			out.print(String.format("<td><a href=?delid=%s>Delete</a></td>", row.get("id")));
			out.println(String.format("<td><a href='Duck17?editid=%s'>Edit</a></td>", row.get("id")));
			out.print("</tr>");
		}
		
		out.print("</table>");
	}
//本來在outHTML當中，放到新的方法ArrayList中	
//	try {
//	//準備一個查詢字串
//	PreparedStatement prst = conn.prepareStatement("Select * from member");
//	//執行查詢字串
//	ResultSet rs = prst.executeQuery();
////	if (rs.last()){
////	System.out.println(rs.getRow());
////}else{
////	System.out.println(0);
////}
//	System.out.println(rs);
//} catch (SQLException e) {
//	
//	e.printStackTrace();
//}

}
