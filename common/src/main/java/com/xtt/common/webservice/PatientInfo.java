
package com.xtt.common.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <pre>
 * &lt;complexType name="patientInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="jsonStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patientInfo", propOrder = { "jsonStr" })
public class PatientInfo {

	protected String jsonStr;

	/**
	 * @return possible object is {@link String }
	 */
	public String getJsonStr() {
		return jsonStr;
	}

	/**
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setJsonStr(String value) {
		this.jsonStr = value;
	}

}
