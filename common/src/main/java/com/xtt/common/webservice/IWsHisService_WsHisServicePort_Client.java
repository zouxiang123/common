
package com.xtt.common.webservice;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 3.1.6 2016-05-26T11:36:17.975+08:00 Generated source version: 3.1.6
 * 
 */
public final class IWsHisService_WsHisServicePort_Client {

	private static final QName SERVICE_NAME = new QName("http://webservice.txgl.xtt.com/", "wsHisService");

	private IWsHisService_WsHisServicePort_Client() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		URL wsdlURL = WsHisService.WSDL_LOCATION;
		if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
			File wsdlFile = new File(args[0]);
			try {
				if (wsdlFile.exists()) {
					wsdlURL = wsdlFile.toURI().toURL();
				} else {
					wsdlURL = new URL(args[0]);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		WsHisService ss = new WsHisService(wsdlURL, SERVICE_NAME);
		IWsHisService port = ss.getWsHisServicePort();

		{
			System.out.println("Invoking patientInfo...");
			java.lang.String _patientInfo_jsonStr = "";
			java.lang.String _patientInfo__return = port.patientInfo(_patientInfo_jsonStr);
			System.out.println("patientInfo.result=" + _patientInfo__return);

		}

		System.exit(0);
	}

}