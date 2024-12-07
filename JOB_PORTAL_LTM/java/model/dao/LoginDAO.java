package model.dao;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.bean.Account;
import utils.DBConnect;
import utils.EncryptionUtil;

public class LoginDAO {
	//contruct instance
	private LoginDAO() {
	}
	private static LoginDAO instance;

	public static LoginDAO getInstance() {
		if (instance == null) {
			instance = new LoginDAO();
		}
		return instance;
	}
	
	//Check account
	public Account checkAccount(String username, String password) {
		//mã hoa mật khẩu
		password = EncryptionUtil.getInstance().encrypt(password, "1234567890123456");
		String query = "SELECT * FROM account WHERE username = ? AND password = ? AND is_deleted = 0";
		List<String> params = new ArrayList<>();
		params.add(username);
		params.add(password);
		ResultSet rs = DBConnect.getInstance().selectSQL(params,query);
		try {
			if (rs.next()) {
				String id = rs.getNString("id");
				String role = rs.getNString("role");
				String avatar_url = rs.getNString("avatar_url");
				Account account = new Account(id, username, role, avatar_url);
				return account;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Sign up
	public boolean signUp(String name, String email, String username, String password, String role) {
		//mã hoa mật khẩu
		password = EncryptionUtil.getInstance().encrypt(password, "1234567890123456");
		
		String query = "INSERT INTO account(id, username, password, avatar_url, role, is_deleted) VALUES(?,?,?,?,?,0)";
		List<String> params = new ArrayList<>();
		String id = createId(username);
		params.add(id);
		params.add(username);
		params.add(password);
		params.add("link");
		params.add(role);
		
		boolean rs = DBConnect.getInstance().dataSQL(params, query);
		if (rs) {
			addProfile(id, id, name, email, role);
		}
		return rs;
	}
	
	//Add candidate profile
	public void addProfile(String id, String id_acc, String fullname, String email, String role) {
		String id_profile = createId(fullname);
		String profile = role + "_profile";
		String query = "INSERT INTO "+ profile + "(id, id_acc, fullname, email, cv_url) VALUES(?,?,?,?,'')";
		List<String> params = new ArrayList<>();
		params.add(id_profile);
		params.add(id_acc);
		params.add(fullname);
		params.add(email);
		DBConnect.getInstance().dataSQL(params, query);
	}
	
	//Create id for account
	public String createId(String username) {
		String rs = username.substring(0, 2).toUpperCase();
		// Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();
        
        // Định dạng thời gian theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        rs += formattedDate;
		return rs;
	}
	
	public static void main(String[] args) {
		LoginDAO loginDAO = LoginDAO.getInstance();
		Account account = loginDAO.checkAccount("Admin", "12345");
		if (account != null) {
			System.out.println("ID: " + account.getId());
			System.out.println("Username: " + account.getUsername());
			System.out.println("Role: " + account.getRole());
			System.out.println("Avatar URL: " + account.getAvatar_url());
		}
		
		account = loginDAO.checkAccount("admin", "12345");
		if (account != null) {
			System.out.println("ID: " + account.getId());
			System.out.println("Username: " + account.getUsername());
			System.out.println("Role: " + account.getRole());
			System.out.println("Avatar URL: " + account.getAvatar_url());
		}
	}
}
