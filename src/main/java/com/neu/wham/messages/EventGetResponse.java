package com.neu.wham.messages;

public class EventGetResponse {
	public enum Status{
		OK, ERROR;
	}
	private Status status;
	private String msg;
	
	public Status getStatus(){
		return status;
	}
	public void setStatus(Status status){
		this.status = status;
	}
	public String getMsg(){
		return msg;
	}
	public void setMsg(String msg){
		this.msg = msg;		
	}
}
