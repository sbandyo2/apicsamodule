package com.ibm.bean;


public class VOWrapperDTO {

	private String fileName = null;

	private StringBuffer requestXml = null;

	private StringBuffer responseXml = null;
	
	private String fileType = null;
	
	private StringBuffer recievedData = null;
	
	

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the recievedData
	 */
	public StringBuffer getRecievedData() {
		return recievedData;
	}

	/**
	 * @param recievedData the recievedData to set
	 */
	public void setRecievedData(StringBuffer recievedData) {
		this.recievedData = recievedData;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the requestXml
	 */
	public StringBuffer getRequestXml() {
		return requestXml;
	}

	/**
	 * @param requestXml
	 *            the requestXml to set
	 */
	public void setRequestXml(StringBuffer requestXml) {
		this.requestXml = requestXml;
	}

	/**
	 * @return the responseXml
	 */
	public StringBuffer getResponseXml() {
		return responseXml;
	}

	/**
	 * @param responseXml
	 *            the responseXml to set
	 */
	public void setResponseXml(StringBuffer responseXml) {
		this.responseXml = responseXml;
	}

}
