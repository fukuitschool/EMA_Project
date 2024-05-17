package model;

import java.io.Serializable;

public class EventEntryStBean implements Serializable {
	private static final long serialVersionUID = 1L;
	//event_entryテーブルの「1レコードあたり」に必要な列名→フィールド化
	private String entryId;
	private String eventId;
	private String studentId;
	
	

	public EventEntryStBean(){}
	
	public EventEntryStBean(String eventId, String studentId) {
		
		this.eventId = eventId;
		this.studentId = studentId;
	}
	public EventEntryStBean(String entryId,String eventId, String studentId) {
		this.entryId = entryId;
		this.eventId = eventId;
		this.studentId = studentId;
	
	}
	
	//値を取得するためのgetter・setter
		public String getEntryId() {
			return entryId;
		} 
		public void setEntryId(String entryId) {
			this.entryId = entryId;
		}
		public String getEventId() {
			return eventId;
		} 
		public void setEventId(String eventId) {
			this.eventId = eventId;
		}
		
		public String getStudentId() { 
			return studentId;  
		}
		public void setStudentId_(String studentId) {
			this.studentId  = studentId;
		}
		
}