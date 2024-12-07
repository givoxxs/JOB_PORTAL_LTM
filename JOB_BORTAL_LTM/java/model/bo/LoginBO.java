package model.bo;

import model.bean.Account;
import model.dao.LoginDAO;

public class LoginBO {
	//contruct instance
	private LoginBO() {
	}
	private static LoginBO instance;

	public static LoginBO getInstance() {
		if (instance == null) {
			instance = new LoginBO();
		}
		return instance;
	}
	
	//Ham kiem tra tai khoan
	public Account checkAccount(String username, String password) {
		return LoginDAO.getInstance().checkAccount(username, password);
	}
}
