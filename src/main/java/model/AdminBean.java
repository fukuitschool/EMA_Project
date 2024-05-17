package model;

public class AdminBean {
	private final String checkpass = "1234"; //認証用パス
	private String adminId; //ユーザーID
	private String adminPass; //パスワード
	private String adminName; //ユーザー名
	private String userType;
	
	public AdminBean() {}
	public AdminBean(String adminId,String adminName) {
		this.adminId = adminId;
		this.adminName= adminName;
	}
	public AdminBean(String adminId,String adminPass,String adminName) {
		this.adminId = adminId;
		this.adminPass = adminPass;
		this.adminName = adminName;
	}
	
	public AdminBean(String adminId, String adminPass, String adminName, String userType) {
		this.adminId = adminId;
		this.adminPass = adminPass;
		this.adminName = adminName;
		this.userType = userType;
	}
	
	public String getAdminId() {return adminId;}
	public String getAdminPass() {return adminPass;}
	public String getAdminName() {return adminName;}
	public String getCheckpass() {
		return checkpass;
	}
	
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String toString() {
	    return "adminId=" + this.getAdminId() + ", adminName=" + this.getAdminName() + ", adminPass=" + this.getAdminPass();
	}
}
