package model;

import java.io.Serializable;

public class EventBean implements Serializable{
	
	@Override
	public String toString() {
		return "EventBean [event_id=" + event_id + ", event_name=" + event_name + ", event_category=" + event_category
				+ ", event_date=" + event_date + ", event_time=" + event_time + ", event_place=" + event_place + "]";
	}

	//eventテーブルの「1レコードあたり」に必要な列名→フィールド化
	String event_id;
	String event_name;
	String event_category;
	String event_date;
	String event_time;
	String event_place;
	
	
	public EventBean() {};
	
	public EventBean(String event_id,String event_name,String event_category,String event_date,String event_time,String event_place) {
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_category = event_category;
		this.event_date = event_date;
		this.event_time = event_time;
		this.event_place = event_place;
	}
	
	//値を取得するためのgetter・setter
	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_category() {
		return event_category;
	}

	public void setEvent_category(String event_category) {
		this.event_category = event_category;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public String getEvent_time() {
		return event_time;
	}

	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}

	public String getEvent_place() {
		return event_place;
	}

	public void setEvent_place(String event_place) {
		this.event_place = event_place;
	}
	
}