package com.ibm.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status = null;
	private String msg = null;
	private Exception ex = null;

	/**
	 * 
	 */
	public ApiError() {

	}

	/**
	 * @param eCode
	 * @param eMsg
	 * @param exception
	 */
	public ApiError(HttpStatus eStatus, String eMsg, Exception exception) {
		status = eStatus;
		msg = eMsg;
		ex = exception;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the ex
	 */
	public Exception getEx() {
		return ex;
	}

	/**
	 * @param ex
	 *            the ex to set
	 */
	public void setEx(Exception ex) {
		this.ex = ex;
	}

}
