package com.neu.wham.messages;

/**
 * Message that is replied for the incoming custom event.
 * Status will be OK, if the request is valid.Otherwise ERROR.
 * The msg will contain the invalid field details, when the invalid request is received.
 * @author Vijet Badigannavar
 */
public class EventPostResponse {
	/**
	 * Status of the Request. OK indicates Success. Error indicates Failure.
	 */
	public enum Status{
		OK,ERROR;
	}
	/**
	 * Status of the received Reqeust
	 */
	private Status status;
	/**
	 * Msg associated with the response.
	 */
	private String msg;
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
