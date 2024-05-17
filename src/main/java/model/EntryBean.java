package model;

import java.io.Serializable;

public class EntryBean implements Serializable{
	String entry_id;
	String event_id;
	String admin_id;
	String student_id;
	String status;
	public EntryBean() {};
	public EntryBean(String event_id,String admin_id) {
		this.event_id = event_id;
		this.admin_id = admin_id;
	}
	public EntryBean(String event_id,String student_id,String status) {
		this.event_id = event_id;
		this.student_id = student_id;
		this.status = status;
	}
	public String getEntry_id() {
		return entry_id;
	}
	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
