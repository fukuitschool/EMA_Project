package model;

public class PasswordSecurityLogic {

public static String secureDisplayPass(String password) {
		
		int length = password.length();
		String hidePass = "*".repeat(length);
		
		if (length >= 4) {
			hidePass = "*".repeat(8);
		}

		return hidePass;
	}

}