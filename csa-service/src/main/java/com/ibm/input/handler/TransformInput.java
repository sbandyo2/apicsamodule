package com.ibm.input.handler;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ibm.bean.LineItemDTO;
import com.ibm.bean.RequisitionDTO;
import com.ibm.consants.CSAServiceConstants;
import com.ibm.exception.ServiceException;

public class TransformInput {

	private static final String END_DATE = "endDate";

	private static final String START_DATE = "startDate";

	private static final String VALUE_ORDER = "valueOrder";

	private static final String LINEITEM_SOURCE_CODE = "lineitemSourceCode";

	private static final String LINEITEM_BY_PASS_FLAG = "lineitemByPassFlag";

	private static final String LINEITEM_CONTRACT_NO = "lineitemContractNo";

	private static final String LINEITEM_SUPPLIER_ID = "lineitemSupplierId";

	private static final String ORIGINATING_SYSTEM_LINE_ITEM_NUMBER = "originatingSystemLineItemNumber";

	private static final String LINEITEM_NEED_BY_DATE = "lineitemNeedByDate";

	private static final String LINE_ITEM_SEQ_NO = "lineItemSeqNo";

	private static final String LINE_ITEM_QTY = "lineItemQty";

	private static final String SPLIT_PERCENTAGE = "splitPercentage";

	private static final String LINE_ITEM_UOM = "lineItemUom";

	private static final String SUPPLIERPART_NUMBER = "supplierpartNumber";

	private static final String LINE_ITEM_CURR_CD = "lineItemCurrCd";

	private static final String LINE_ITEM_AMOUNT = "lineItemAmount";

	private static final String LINEITEM_DESCRIPTION = "lineitemDescription";

	private static final String UNSPSC_CD = "unspsc_cd";

	private static final String LINE_ITEMS = "LineItems";

	private static final String REQUESTER_WEB_ID = "RequesterWebId";

	private static final String PREPARER_WEB_ID = "PreparerWebId";

	private static final String COMMENT_TO_SUPPLIER = "CommentToSupplier";

	private static final String REQUESTER_NAME = "RequesterName";

	private static final String COMPANY_CODE = "CompanyCode";

	private static final String REQUISITION_NAME = "RequisitionName";

	private static final String UNIQUE_TRANSACTION_NUMBER = "UniqueTransactionNumber";

	Logger logger = LoggerFactory.getLogger(TransformInput.class);
	
	private List<LineItemDTO> lineItems = null;
	
	
	/**
	 * The method parses the request data and transforms it into RequisitionDTO
	 * for succeeding microservices
	 * 
	 * @param requestData
	 * @return
	 */
	public RequisitionDTO transformInput(String requestData)  {
		Document xmlDoc = null;
		RequisitionDTO requisitionDTO = null;
		
		try {
		
			// prepare the key value pair by parsing the xml
			xmlDoc = convertStringToDocument(requestData);
			
			Element e = xmlDoc.getDocumentElement();
			NodeList children = null;
			Node childNode = null;
			children = e.getChildNodes();
			String elemVal = null;
			String elementname = null;
			requisitionDTO = new RequisitionDTO();
			
			
			
			for (int i = 0; i < children.getLength(); i++) {
				childNode = children.item(i);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {

					// get the element value
					elemVal = childNode.getTextContent();
					elementname = childNode.getNodeName();

					if(UNIQUE_TRANSACTION_NUMBER.equalsIgnoreCase(elementname)){
						if(elemVal!=null && elemVal.startsWith(CSAServiceConstants.F)){
							//set the application type
							requisitionDTO.setApplicationType(CSAServiceConstants.APP_TYPE_FP);
						}else
							//set the application type
							requisitionDTO.setApplicationType(CSAServiceConstants.APP_TYPE_TM);
						requisitionDTO.setApplicationTransactionNumber(elemVal);
					}
					else if(REQUISITION_NAME.equalsIgnoreCase(elementname)){
						requisitionDTO.setRequisitionName(elemVal);
					}
					else if(COMPANY_CODE.equalsIgnoreCase(elementname)){
						requisitionDTO.setCo_cd(elemVal);
					}else if(REQUESTER_NAME.equalsIgnoreCase(elementname)){
						requisitionDTO.setRequesterName(elemVal);
					}else if(COMMENT_TO_SUPPLIER.equalsIgnoreCase(elementname)){
						requisitionDTO.setCommentToSupplier(elemVal);
					}else if(PREPARER_WEB_ID.equalsIgnoreCase(elementname)){
						requisitionDTO.setPreparerWebId(elemVal);
					}else if(REQUESTER_WEB_ID.equalsIgnoreCase(elementname)){
						requisitionDTO.setRequesterWebId(elemVal);
					}else if(LINE_ITEMS.equalsIgnoreCase(elementname)){
						
						LineItemDTO itemDTO = parseXml(xmlDoc, (Element) childNode,null);
						lineItems.add(itemDTO);
					}
				}
			}
			
		requisitionDTO.setLineItemDTOs(lineItems);
		
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
		} catch (SecurityException e) {
			logger.error(e.getMessage());
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		
		return requisitionDTO;
	}
	
	/**
	 * This method parses the xml document to the last child level and get the
	 * values based on the xsd defined elements
	 * @param doc
	 * @param dto
	 * @param e
	 * @throws ServiceException 
	 */
	private LineItemDTO  parseXml(Document doc, final Element e,LineItemDTO dto) throws ServiceException {
		
		
		NodeList children = null;
		Node childNode = null;
		children = e.getChildNodes();
		String elemVal = null;
		String elementname = null;
		
		
		try{
			for (int i = 0; i < children.getLength(); i++) {
				childNode = children.item(i);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {

					// get the element value
					elemVal = childNode.getTextContent();
					elementname = childNode.getNodeName();
					
					if("ItemDetails".equalsIgnoreCase(elementname)){
						//initializing for the first time
						if(lineItems== null)
							lineItems = new ArrayList<>();
						
						if(dto!=null){
							lineItems.add(dto);
						}
							
						dto = new LineItemDTO();
					}else{
						//populate line item
						populateLineitem(dto, elemVal, elementname);
					}
						
					parseXml(doc, (Element) childNode,dto);
				}
			}
			
		}catch(Exception ex){
			throw new ServiceException (ex);
		}
		
		return dto;
	}

	/**
	 * @param dto
	 * @param elemVal
	 * @param elementname
	 */
	private void populateLineitem(LineItemDTO dto, String elemVal,
			String elementname) {
		if(elementname.equalsIgnoreCase(UNSPSC_CD)){
			dto.setUnspsc_cd(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINEITEM_DESCRIPTION)){
			dto.setLineitemDescription(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINE_ITEM_AMOUNT)){
			dto.setLineItemAmount(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINE_ITEM_CURR_CD)){
			dto.setLineItemCurrCd(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(SUPPLIERPART_NUMBER)){
			dto.setSupplierpartNumber(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINE_ITEM_UOM)){
			dto.setLineItemUom(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(SPLIT_PERCENTAGE)){
			dto.setSplitPercentage(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINE_ITEM_QTY)){
			dto.setLineItemQty(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINE_ITEM_SEQ_NO)){
			dto.setLineItemSeqNo(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINEITEM_NEED_BY_DATE)){
			dto.setLineitemNeedByDate(elemVal);
		}
		if(elementname.equalsIgnoreCase(ORIGINATING_SYSTEM_LINE_ITEM_NUMBER)){
			dto.setOriginatingSystemLineItemNumber(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINEITEM_SUPPLIER_ID)){
			dto.setLineitemSupplierId(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINEITEM_CONTRACT_NO)){
			dto.setLineitemContractNo(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINEITEM_BY_PASS_FLAG)){
			dto.setLineitemByPassFlag(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(LINEITEM_SOURCE_CODE)){
			dto.setLineitemSourceCode(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(VALUE_ORDER)){
			dto.setValueOrder(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(START_DATE)){
			dto.setStartDate(elemVal);
		}
		
		if(elementname.equalsIgnoreCase(END_DATE)){
			dto.setEndDate(elemVal);
		}
	}
	
	/**
	 * Converts the return Xml to Document
	 * @param xmlStr
	 * @return
	 * @throws GDException
	 */
	private  Document convertStringToDocument(String responseXml) throws ServiceException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder = null;  
        Document doc = null;
        
        try  
        {  
            builder = factory.newDocumentBuilder();  
            doc = builder.parse( new InputSource( new StringReader( responseXml ) ) ); 
            
        } catch (Exception e) {  
        	throw new ServiceException(e.getMessage()); 
        } 
        return doc;
    }
	
	public static void main(String[] args) {
		String requestData = null;
		
		/*requestData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><UniqueTransactionNumber>F22345</UniqueTransactionNumber>"
				+ "<RequisitionName>China Request _Ariba Testing1(F239LY)</RequisitionName><CompanyCode>0684</CompanyCode><RequesterName>OneCN Requester</RequesterName>"
				+ "<CommentToSupplier>Comments to suppliers * Contracts: None</CommentToSupplier><PreparerWebId>chinareq1@c25a0161.toronto.ca.ibm.com</PreparerWebId><RequesterWebId>chinareq1@c25a0161.toronto.ca.ibm.com</RequesterWebId>"
				+ "<LineItems>"
					+ "<ItemDetails>"
						+ "<unspsc_cd>81111600</unspsc_cd>"
						+ "<lineitemDescription>Milestone name1~Description of milestone1</lineitemDescription>"
						+ "<lineItemAmount>20.000</lineItemAmount>"
						+ "<lineItemCurrCd>CNY</lineItemCurrCd>"
						+ "<supplierpartNumber>F239LY000001</supplierpartNumber>"
						+ "<lineItemUom>DAY</lineItemUom>"
						+ "<splitPercentage>100</splitPercentage>"
						+ "<lineItemQty>3</lineItemQty> <!-- accepts 3.000  -->"
						+ "<lineItemSeqNo>1</lineItemSeqNo>"
						+ "<!-- <lineItemComment></lineItemComment> --> <!-- optional -->"
						+ "<lineitemNeedByDate>2021-01-02T00:00:00</lineitemNeedByDate>"
						+ "<originatingSystemLineItemNumber>001</originatingSystemLineItemNumber>"
						+ "<lineitemSupplierId>1000301665</lineitemSupplierId> <!--  Please note your supplier Id exist in ierp otherwise transaction will fail -->"
						+ "<lineitemContractNo></lineitemContractNo>"
						+ "<lineitemByPassFlag>B</lineitemByPassFlag>"
						+ "<lineitemSourceCode>E</lineitemSourceCode>"
						+ "<valueOrder>false</valueOrder> <!-- optional : accepts true and false based on UOM value -->"
						+ "<startDate>2021-01-02T00:00:00</startDate>"
						+ "<endDate>2022-01-02T00:00:00</endDate>"
					+ "</ItemDetails>"
					+ "<ItemDetails>"
						+ "<unspsc_cd>90000000TS</unspsc_cd>"
							+ "<lineitemDescription>Milestone name1~Description of milestone1</lineitemDescription>"
						+ "<lineItemAmount>80.000</lineItemAmount>"
						+ "<lineItemCurrCd>CNY</lineItemCurrCd>"
						+ "<supplierpartNumber>F239LY000002</supplierpartNumber>"
						+ "<lineItemUom>DAY</lineItemUom>"
						+ "<splitPercentage>100</splitPercentage>"
						+ "<lineItemQty>1</lineItemQty> <!-- accepts 3.000  -->"
						+ "<lineItemSeqNo>2</lineItemSeqNo>"
						+ "<!-- <lineItemComment></lineItemComment> --> <!-- optional -->"
						+ "<lineitemNeedByDate>2021-01-02T00:00:00</lineitemNeedByDate>"
						+ "<originatingSystemLineItemNumber>002</originatingSystemLineItemNumber>"
						+ "<lineitemSupplierId>1000301665</lineitemSupplierId> <!--  Please note your supplier Id exist in ierp otherwise transaction will fail -->"
						+ "<lineitemContractNo></lineitemContractNo>"
						+ "<lineitemByPassFlag>B</lineitemByPassFlag>"
						+ "<lineitemSourceCode>E</lineitemSourceCode>"
						+ "<valueOrder>true</valueOrder> <!-- optional : accepts true and false based on UOM value -->"
						+ "<startDate>2021-01-02T00:00:00</startDate>"
						+ "<endDate>2022-01-02T00:00:00</endDate>"
				+ "	</ItemDetails>"
			+ "</LineItems>"
			+ "</Request>";*/
		
		requestData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<Request xmlns=\"http://www.w3.org/2001/XMLSchema-instance\">"
					+ "<UniqueTransactionNumber>F224ZD_1</UniqueTransactionNumber>"
  + "<RequisitionName>Milestone Type Cat (Copy) (Copy)(F224ZD)</RequisitionName>"
  + "<CompanyCode>0147</CompanyCode>"
  + "<RequesterName>America Tester6</RequesterName>"
  + "<CommentToSupplier>Resubmit Comments to suppliers * Contracts:~P-SEA~CS-02-394</CommentToSupplier>"
  + "<PreparerWebId>wrwood2@us.ibm.com</PreparerWebId>"
  + "<RequesterWebId>csadev16@v25db510.mkm.can.ibm.com</RequesterWebId>"
  + "<LineItems>"
    + "<ItemDetails>"
      + "<unspsc_cd>86101700</unspsc_cd>"
      + "<lineitemDescription>Java develover~he should have good knowledge of kakak</lineitemDescription>"
      + "<lineItemAmount>90.000</lineItemAmount>"
      + "<lineItemCurrCd>USD</lineItemCurrCd>"
      + "<supplierpartNumber>F224ZD000001</supplierpartNumber>"
      + "<lineItemUom>DAY</lineItemUom>"
      + "<splitPercentage>100</splitPercentage>"
      + "<lineItemQty>12.000</lineItemQty>"
      + "<lineItemSeqNo>001</lineItemSeqNo>"
      + "<lineitemNeedByDate>2018-11-22T00:00:00</lineitemNeedByDate>"
      + "<originatingSystemLineItemNumber>001</originatingSystemLineItemNumber>"
      + "<lineitemSupplierId>1000310441</lineitemSupplierId>"
      + "<lineitemContractNo>CS-02-394</lineitemContractNo>"
      + "<lineitemByPassFlag>B</lineitemByPassFlag>"
      + "<lineitemSourceCode>E</lineitemSourceCode>"
      + "<valueOrder>false</valueOrder>"
      + "<startDate>2018-11-22T00:00:00</startDate>"
+ "      <endDate>2018-11-23T00:00:00</endDate>"
   + " </ItemDetails>"
  + "</LineItems>"
+ "</Request>";
		RequisitionDTO dto = new TransformInput().transformInput(requestData);
		
		System.out.println("done" +dto.getLineItemDTOs().size());
	}
}
