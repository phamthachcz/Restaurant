package jat.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


import jat.dao.AccountDAO;
import jat.dto.Account;



@SessionScoped
@Named
public class AccountController implements Serializable {
	
	private Account editedAccount;
	
	private Account loginAccount;
	
	private boolean isLoginAcc;
	
	private boolean isAdminLogin;
	
	private boolean isUsersLogin;
	
	public boolean isUsersLogin() {
		return isUsersLogin;
	}

	public void setUserLogin(boolean isUserLogin) {
		this.isUsersLogin = isUserLogin;
	}

	public boolean isAdminLogin() {
		return isAdminLogin;
	}

	public void setAdminLogin(boolean isAdminLogin) {
		this.isAdminLogin = isAdminLogin;
	}

	public boolean isLoginAcc() {
		return isLoginAcc;
	}

	public void setLoginAcc(boolean isLoginAcc) {
		this.isLoginAcc = isLoginAcc;
	}

	private boolean isLogoutAcc;
	
	public boolean isLogoutAcc() {
		return isLogoutAcc;
	}

	public void setLogoutAcc(boolean isLogoutAcc) {
		this.isLogoutAcc = isLogoutAcc;
	}

	public Account getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(Account loginAccount) {
		this.loginAccount = loginAccount;
	}

	public Account getEditedAccount() {
		return editedAccount;
	}

	public void setEditedAccount(Account editedAccount) {
		this.editedAccount = editedAccount;
	}

	@Inject
	private AccountDAO accountDAO;
	
	public AccountController() {
		isLoginAcc = false;
		isLogoutAcc = true;
		isAdminLogin = false;
		isUsersLogin = false;
	}
	@PostConstruct
	public void init() {
		
		Account admin = new Account();
		admin.setId(1);
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("pha0027@vsb.cz");
		admin.setPhone("7473342306");
		admin.setIsLogin(false);
		admin.setRole(0);
		accountDAO.save(admin);
		
		
	}
	
	public List<Account> getAllAccounts() {
		return accountDAO.getAll(); 
	}

	public String save() {
		editedAccount.setRole(1);
		accountDAO.save(editedAccount);
		editedAccount = null;
		return "index";
	}
	public String index() {
		return "index";
	}
	
	public String loginPage() {
		HttpSession s = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true));
		Account account_login = (Account)s.getAttribute("loginAccount");
		
		if(account_login == null) {
			loginAccount = new Account();
			return "login";
		}
		else {
			loginAccount = account_login;
			return "adminPage";
		}
		
	}
	
	public String logout() {
		HttpSession s = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true));
		
		s.setAttribute("loginAccount", null);
		loginAccount = null;
		isLoginAcc = false;
		isLogoutAcc = true;
		isAdminLogin = false;
		isUsersLogin = false;
		return "index";
	}
	
	public String userLogin() {
		HttpSession s = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true));
		Account acc = accountDAO.findbyUsername(loginAccount.getUsername().trim(), loginAccount.getPassword().trim());
		
		if(acc == null) {
			return "login";
		}
		else {
			isLoginAcc = true;
			isLogoutAcc = false;
			s.setAttribute("loginAccount", acc);
			
			if(acc.getRole() == 0) {
				isAdminLogin = true;
				return "adminPage";
			}
			else{
				isUsersLogin = true;
				return "index";
			}
		}
	}
	
	public String register() {
		editedAccount = new Account();
		
		if(loginAccount == null) {
			return "register";
		}
		else {
			if(loginAccount.getUsername() == null) {
				return "register";
			}
			if(loginAccount.getRole() == 0) {
				return "editAccount";
			}
			else {
				return "index";
			}
		}
		
	}
	
	public String saveAccount() {
		accountDAO.save(editedAccount);
		editedAccount = null;
		return "userManage";
	}
	
	public String accountManage() {
		return "userManage";
	}
	
	public String editAccount(Account edit) {
		editedAccount = edit;
		return "editAccount";
	}
	
	public String removeAccount(Account edit) {
		accountDAO.delete(edit);
		editedAccount = null;
		return "userManage";
	}
	
	
}
