package model;

import java.io.Serializable;

public class UsersBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userName;
	private String password;
	
	public UsersBean() {}
	
	public UsersBean(String userId) {
		this.userId = userId;
	};

	public UsersBean(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	};
	
	
	public UsersBean(String userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UsersBean [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	};	
}
