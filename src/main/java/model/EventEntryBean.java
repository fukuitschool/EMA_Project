package model;

import java.io.Serializable;

public class EventEntryBean implements Serializable {
	 private static final long serialVersionUID = 1L;

	    private int entryId;
	    private String eventId;
	    private String adminId;
	    private String studentId;
	    private String adminName;
	    private String studentName;
	    private String status;

	    public EventEntryBean() {
	    }

	    public EventEntryBean(String eventId, String adminId, String studentId, String status) {
	        this.eventId = eventId;
	        this.adminId = adminId;
	        this.studentId = studentId;
	        this.status = status;
	    }

	    public EventEntryBean(int entryId, String eventId, String adminId, String studentId, String status) {
	        this.entryId = entryId;
	        this.eventId = eventId;
	        this.adminId = adminId;
	        this.studentId = studentId;
	        this.status = status;
	    }

	    public EventEntryBean(int entryId, String eventId, String adminId, String studentId, String adminName,
	                           String studentName, String status) {
	        this.entryId = entryId;
	        this.eventId = eventId;
	        this.adminId = adminId;
	        this.studentId = studentId;
	        this.adminName = adminName;
	        this.studentName = studentName;
	        this.status = status;
	    }

	    public EventEntryBean(String status) {
	        this.status = status;
	    }

	    public EventEntryBean(int entryId2, String eventId2, String adminId2, String stId, String adminName2,
	                           String status) {
	        this.entryId = entryId2;
	        this.eventId = eventId2;
	        this.adminId = adminId2;
	        this.studentId = stId;
	        this.adminName = adminName2;
	        this.status = status;
	    }

	    public int getEntryId() {
	        return entryId;
	    }

	    public void setEntryId(int entryId) {
	        this.entryId = entryId;
	    }

	    public String getEventId() {
	        return eventId;
	    }

	    public void setEventId(String eventId) {
	        this.eventId = eventId;
	    }

	    public String getAdminId() {
	        return adminId;
	    }

	    public void setAdminId(String adminId) {
	        this.adminId = adminId;
	    }

	    public String getStudentId() {
	        return studentId;
	    }

	    public void setStudentId(String studentId) {
	        this.studentId = studentId;
	    }

	    public String getAdminName() {
	        return adminName;
	    }

	    public void setAdminName(String adminName) {
	        this.adminName = adminName;
	    }

	    public String getStudentName() {
	        return studentName;
	}

	    public void setStudentName(String studentName) {
	        this.studentName = studentName;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    @Override
	    public String toString() {
	        return "EventEntryBean [entryId=" + entryId + ", eventId=" + eventId + ", adminId=" + adminId + ", studentId="
	                + studentId + ", status=" + status + ", adminName=" + adminName + ", studentName=" + studentName + "]";
	    }
}
