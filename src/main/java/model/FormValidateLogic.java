package model;

import java.sql.SQLException;

import dao.AdminDAO;
import dao.StudentDAO;

public class FormValidateLogic {
	
	private StudentDAO studentDao;
	private AdminDAO adminDao;
	
	public FormValidateLogic(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
	public FormValidateLogic(AdminDAO adminDao) {
		this.adminDao = adminDao;
	}

	public boolean isValidId(String studentId) {
		
		return studentId.matches("[0-9]{4}");
	}
	
	public boolean isValidPass(String studentPass) {
		
		return studentPass.matches("[0-9]{8}");
	}
	
	
	public boolean isStudentIdAvailable(String studentId) throws SQLException {
		
		try {
			System.out.println("Id is available");
			return studentDao.isIdAvailable(studentId);
		} catch (Exception e) {
			throw new SQLException("studeentID already exisits: " + e);
		}
	}
	
	
	public boolean isAdminIdAvailable(String id) throws SQLException {
		
		try {
			System.out.println("Id is available");
			return adminDao.isIdAvailable(id);
		} catch (Exception e) {
			throw new SQLException("adminId already exisits: " + e);
		}
	}
	
}
