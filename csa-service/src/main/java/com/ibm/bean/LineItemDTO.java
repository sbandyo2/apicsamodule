package com.ibm.bean;

public class LineItemDTO {

	private String unspsc_cd;
	private String lineitemDescription;
	private String lineItemAmount;
	private String lineItemCurrCd;
	private String supplierpartNumber;
	private String lineItemUom;

	private String splitPercentage;
	// auto-generated sequence like 001,002 etc
	private String lineItemQty;
	private String lineItemSeqNo;
	private String lineItemComment;

	private String lineitemNeedByDate;
	// if any or else store auto-generated sequence like lineitemseqNo field
	private String originatingSystemLineItemNumber;
	private String lineitemSupplierId;

	private String lineitemContractNo;
	private String lineitemByPassFlag;
	private String lineitemSourceCode;

	private String valueOrder;

	private String startDate;
	private String endDate;

	private String costCentre;
	private String wbsElement;

	
	/**
	 * @return the costCentre
	 */
	public String getCostCentre() {
		return costCentre;
	}

	/**
	 * @param costCentre the costCentre to set
	 */
	public void setCostCentre(String costCentre) {
		this.costCentre = costCentre;
	}

	/**
	 * @return the wbsElement
	 */
	public String getWbsElement() {
		return wbsElement;
	}

	/**
	 * @param wbsElement the wbsElement to set
	 */
	public void setWbsElement(String wbsElement) {
		this.wbsElement = wbsElement;
	}

	/**
	 * @return the lineItemCurrCd
	 */
	public String getLineItemCurrCd() {
		return lineItemCurrCd;
	}

	/**
	 * @param lineItemCurrCd the lineItemCurrCd to set
	 */
	public void setLineItemCurrCd(String lineItemCurrCd) {
		this.lineItemCurrCd = lineItemCurrCd;
	}

	/**
	 * @return the unspsc_cd
	 */
	public String getUnspsc_cd() {
		return unspsc_cd;
	}

	/**
	 * @param unspsc_cd
	 *            the unspsc_cd to set
	 */
	public void setUnspsc_cd(String unspsc_cd) {
		this.unspsc_cd = unspsc_cd;
	}

	/**
	 * @return the lineitemDescription
	 */
	public String getLineitemDescription() {
		return lineitemDescription;
	}

	/**
	 * @param lineitemDescription
	 *            the lineitemDescription to set
	 */
	public void setLineitemDescription(String lineitemDescription) {
		this.lineitemDescription = lineitemDescription;
	}

	/**
	 * @return the lineItemAmount
	 */
	public String getLineItemAmount() {
		return lineItemAmount;
	}

	/**
	 * @param lineItemAmount
	 *            the lineItemAmount to set
	 */
	public void setLineItemAmount(String lineItemAmount) {
		this.lineItemAmount = lineItemAmount;
	}

	/**
	 * @return the supplierpartNumber
	 */
	public String getSupplierpartNumber() {
		return supplierpartNumber;
	}

	/**
	 * @param supplierpartNumber
	 *            the supplierpartNumber to set
	 */
	public void setSupplierpartNumber(String supplierpartNumber) {
		this.supplierpartNumber = supplierpartNumber;
	}

	/**
	 * @return the lineItemUom
	 */
	public String getLineItemUom() {
		return lineItemUom;
	}

	/**
	 * @param lineItemUom
	 *            the lineItemUom to set
	 */
	public void setLineItemUom(String lineItemUom) {
		this.lineItemUom = lineItemUom;
	}

	/**
	 * @return the splitPercentage
	 */
	public String getSplitPercentage() {
		return splitPercentage;
	}

	/**
	 * @param splitPercentage
	 *            the splitPercentage to set
	 */
	public void setSplitPercentage(String splitPercentage) {
		this.splitPercentage = splitPercentage;
	}

	/**
	 * @return the lineItemQty
	 */
	public String getLineItemQty() {
		return lineItemQty;
	}

	/**
	 * @param lineItemQty
	 *            the lineItemQty to set
	 */
	public void setLineItemQty(String lineItemQty) {
		this.lineItemQty = lineItemQty;
	}

	/**
	 * @return the lineItemSeqNo
	 */
	public String getLineItemSeqNo() {
		return lineItemSeqNo;
	}

	/**
	 * @param lineItemSeqNo
	 *            the lineItemSeqNo to set
	 */
	public void setLineItemSeqNo(String lineItemSeqNo) {
		this.lineItemSeqNo = lineItemSeqNo;
	}

	/**
	 * @return the lineItemComment
	 */
	public String getLineItemComment() {
		return lineItemComment;
	}

	/**
	 * @param lineItemComment
	 *            the lineItemComment to set
	 */
	public void setLineItemComment(String lineItemComment) {
		this.lineItemComment = lineItemComment;
	}

	/**
	 * @return the lineitemNeedByDate
	 */
	public String getLineitemNeedByDate() {
		return lineitemNeedByDate;
	}

	/**
	 * @param lineitemNeedByDate
	 *            the lineitemNeedByDate to set
	 */
	public void setLineitemNeedByDate(String lineitemNeedByDate) {
		this.lineitemNeedByDate = lineitemNeedByDate;
	}

	/**
	 * @return the originatingSystemLineItemNumber
	 */
	public String getOriginatingSystemLineItemNumber() {
		return originatingSystemLineItemNumber;
	}

	/**
	 * @param originatingSystemLineItemNumber
	 *            the originatingSystemLineItemNumber to set
	 */
	public void setOriginatingSystemLineItemNumber(
			String originatingSystemLineItemNumber) {
		this.originatingSystemLineItemNumber = originatingSystemLineItemNumber;
	}

	/**
	 * @return the lineitemSupplierId
	 */
	public String getLineitemSupplierId() {
		return lineitemSupplierId;
	}

	/**
	 * @param lineitemSupplierId
	 *            the lineitemSupplierId to set
	 */
	public void setLineitemSupplierId(String lineitemSupplierId) {
		this.lineitemSupplierId = lineitemSupplierId;
	}

	/**
	 * @return the lineitemContractNo
	 */
	public String getLineitemContractNo() {
		return lineitemContractNo;
	}

	/**
	 * @param lineitemContractNo
	 *            the lineitemContractNo to set
	 */
	public void setLineitemContractNo(String lineitemContractNo) {
		this.lineitemContractNo = lineitemContractNo;
	}

	/**
	 * @return the lineitemByPassFlag
	 */
	public String getLineitemByPassFlag() {
		return lineitemByPassFlag;
	}

	/**
	 * @param lineitemByPassFlag
	 *            the lineitemByPassFlag to set
	 */
	public void setLineitemByPassFlag(String lineitemByPassFlag) {
		this.lineitemByPassFlag = lineitemByPassFlag;
	}

	/**
	 * @return the lineitemSourceCode
	 */
	public String getLineitemSourceCode() {
		return lineitemSourceCode;
	}

	/**
	 * @param lineitemSourceCode
	 *            the lineitemSourceCode to set
	 */
	public void setLineitemSourceCode(String lineitemSourceCode) {
		this.lineitemSourceCode = lineitemSourceCode;
	}

	/**
	 * @return the valueOrder
	 */
	public String getValueOrder() {
		return valueOrder;
	}

	/**
	 * @param valueOrder
	 *            the valueOrder to set
	 */
	public void setValueOrder(String valueOrder) {
		this.valueOrder = valueOrder;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
