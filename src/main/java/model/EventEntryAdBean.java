package model;

import java.io.Serializable;

public class EventEntryAdBean implements Serializable {
	private static final long serialVersionUID = 1L;
	//event_entryテーブルの「1レコードあたり」に必要な列名→フィールド化
	private String entryId;
	private String eventId;
	private String adminId;
	
	

	public EventEntryAdBean(){}
	
	public EventEntryAdBean(String eventId, String adminId) {
		
		this.eventId = eventId;
		this.adminId = adminId;
	}
	public EventEntryAdBean(String entryId,String eventId, String adminId) {
		this.entryId = entryId;
		this.eventId = eventId;
		this.adminId = adminId;
	
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
		
		public String getAdminId() { 
			return adminId;  
		}
		public void setAdminId_(String adminId) {
			this.adminId  = adminId;
		}
		
}