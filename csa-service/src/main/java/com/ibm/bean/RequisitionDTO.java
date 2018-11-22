package com.ibm.bean;

import java.util.List;

public class RequisitionDTO {

	private String applicationType;
	private String applicationTransactionNumber;

	// transaction Number , for certain upstream it is appended with project
	// name of the upstream
	private String requisitionName;
	private String co_cd;
	private String requesterName;
	private String commentToSupplier;

	private List<LineItemDTO> lineItemDTOs = null;

	private String preparerWebId;
	private String requesterWebId;

	/**
	 * @return the preparerWebId
	 */
	public String getPreparerWebId() {
		return preparerWebId;
	}

	/**
	 * @param preparerWebId
	 *            the preparerWebId to set
	 */
	public void setPreparerWebId(String preparerWebId) {
		this.preparerWebId = preparerWebId;
	}

	/**
	 * @return the requesterWebId
	 */
	public String getRequesterWebId() {
		return requesterWebId;
	}

	/**
	 * @param requesterWebId
	 *            the requesterWebId to set
	 */
	public void setRequesterWebId(String requesterWebId) {
		this.requesterWebId = requesterWebId;
	}

	/**
	 * @return the requisitionName
	 */
	public String getRequisitionName() {
		return requisitionName;
	}

	/**
	 * @param requisitionName
	 *            the requisitionName to set
	 */
	public void setRequisitionName(String requisitionName) {
		this.requisitionName = requisitionName;
	}

	/**
	 * @return the commentToSupplier
	 */
	public String getCommentToSupplier() {
		return commentToSupplier;
	}

	/**
	 * @param commentToSupplier
	 *            the commentToSupplier to set
	 */
	public void setCommentToSupplier(String commentToSupplier) {
		this.commentToSupplier = commentToSupplier;
	}

	/**
	 * @return the lineItemDTOs
	 */
	public List<LineItemDTO> getLineItemDTOs() {
		return lineItemDTOs;
	}

	/**
	 * @param lineItemDTOs
	 *            the lineItemDTOs to set
	 */
	public void setLineItemDTOs(List<LineItemDTO> lineItemDTOs) {
		this.lineItemDTOs = lineItemDTOs;
	}

	/**
	 * @return the requesterName
	 */
	public String getRequesterName() {
		return requesterName;
	}

	/**
	 * @param requesterName
	 *            the requesterName to set
	 */
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	/**
	 * @return the co_cd
	 */
	public String getCo_cd() {
		return co_cd;
	}

	/**
	 * @param co_cd
	 *            the co_cd to set
	 */
	public void setCo_cd(String co_cd) {
		this.co_cd = co_cd;
	}

	/**
	 * @return the applicationType
	 */
	public String getApplicationType() {
		return applicationType;
	}

	/**
	 * @param applicationType
	 *            the applicationType to set
	 */
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	/**
	 * @return the applicationTransactionNumber
	 */
	public String getApplicationTransactionNumber() {
		return applicationTransactionNumber;
	}

	/**
	 * @param applicationTransactionNumber
	 *            the applicationTransactionNumber to set
	 */
	public void setApplicationTransactionNumber(
			String applicationTransactionNumber) {
		this.applicationTransactionNumber = applicationTransactionNumber;
	}

}
