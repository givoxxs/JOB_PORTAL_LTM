package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBConnect {
	//contruct instance
	private DBConnect() {
	}
	private static DBConnect instance;

	public static DBConnect getInstance() {
		if (instance == null) {
			instance = new DBConnect();
		}
		return instance;
	}
	
	//database connection
	public static String url = "jdbc:mysql://localhost:3306/job_portal";
	
	//Ham ket noi csdl
		public Connection connect() {
			Connection con = null;
			try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,"root","");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}
		
	//update database
	public	boolean dataSQL(List<String> params, String query) {
		    Connection conn = getInstance().connect();
		    try {
		        PreparedStatement pr = conn.prepareStatement(query);

		        // Gán các tham số từ danh sách vào PreparedStatement
		        for (int i = 0; i < params.size(); i++) {
		            pr.setString(i + 1, params.get(i)); // Tham số PreparedStatement bắt đầu từ 1
		        }

		        // Thực hiện câu lệnh query
		        int rowsAffected = pr.executeUpdate();

		        // Đóng kết nối
		        pr.close();
		        conn.close();

		        return rowsAffected > 0; // Trả về true nếu có ít nhất một hàng bị ảnh hưởng
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
	//Select database
	public ResultSet selectSQL(List<String> params, String query) {
		Connection conn = getInstance().connect();
		ResultSet rs = null;
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					pr.setString(i + 1, params.get(i));
				}
			}
			rs = pr.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
