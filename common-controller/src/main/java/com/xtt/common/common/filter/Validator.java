package com.xtt.common.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.platform.util.secret.HardWareUtil;

public class Validator extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = -6838081135391333931L;
	Logger log = LoggerFactory.getLogger(Validator.class);

	public void init() {
		if (!HardWareUtil.validateLicense()) {
			log.info("********************************************************");
			System.exit(0);
		}
	}
}