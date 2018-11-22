package com.ibm.exception;

public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode = 0;
	private String errorDisplayCode = null;

	public ServiceException(Exception e)
	{
		super(e.getMessage());
	}
	/**
	 * Constructor
	 * @param message
	 */
	public ServiceException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor
	 * @param code
	 * @param message
	 */
	public ServiceException(int code, String message)
	{
		super(message);
		setErrorCode(code);
	}

	/**
	 * Constructor
	 * @param code
	 * @param message
	 */
	public ServiceException(String displayCode, String message)
	{
		super(message);
		setErrorDisplayCode(displayCode);
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorDisplayCode() {
		return errorDisplayCode;
	}

	public void setErrorDisplayCode(String errorDisplayCode) {
		this.errorDisplayCode = errorDisplayCode;
	}
}
