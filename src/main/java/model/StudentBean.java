package model;

import java.io.Serializable;

public class StudentBean implements Serializable {
    private static final long serialVersionUID = 1L;

	private String studentId;
    private String studentPass;
    private String studentName;
    private String userType;

    public StudentBean() {}
	
    public StudentBean(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

	public StudentBean(String studentId, String studentPass, String studentName) {
		this.studentId = studentId;
		this.studentPass = studentPass;
		this.studentName = studentName;
	}
	
    public StudentBean(String studentId, String studentPass, String studentName, String userType) {
		super();
		this.studentId = studentId;
		this.studentPass = studentPass;
		this.studentName = studentName;
		this.userType = userType;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentPass() {
		return studentPass;
	}

	public void setStudentPass(String studentPass) {
		this.studentPass = studentPass;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Override
	public String toString() {
		return "StudentBeans [studentId=" + studentId + ", studentPass=" + studentPass + ", studentName=" + studentName
				+ ", studentImage=" + "]";
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
