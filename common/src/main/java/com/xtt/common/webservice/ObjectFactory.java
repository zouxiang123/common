
package com.xtt.common.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the com.xtt.txgl.webservice package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML
 * content can consist of schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _PatientInfo_QNAME = new QName("http://webservice.txgl.xtt.com/", "patientInfo");
	private final static QName _PatientInfoResponse_QNAME = new QName("http://webservice.txgl.xtt.com/", "patientInfoResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xtt.txgl.webservice
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link PatientInfo }
	 * 
	 */
	public PatientInfo createPatientInfo() {
		return new PatientInfo();
	}

	/**
	 * Create an instance of {@link PatientInfoResponse }
	 * 
	 */
	public PatientInfoResponse createPatientInfoResponse() {
		return new PatientInfoResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link PatientInfo }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.txgl.xtt.com/", name = "patientInfo")
	public JAXBElement<PatientInfo> createPatientInfo(PatientInfo value) {
		return new JAXBElement<PatientInfo>(_PatientInfo_QNAME, PatientInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link PatientInfoResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.txgl.xtt.com/", name = "patientInfoResponse")
	public JAXBElement<PatientInfoResponse> createPatientInfoResponse(PatientInfoResponse value) {
		return new JAXBElement<PatientInfoResponse>(_PatientInfoResponse_QNAME, PatientInfoResponse.class, null, value);
	}

}
