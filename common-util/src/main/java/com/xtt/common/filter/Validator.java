package com.xtt.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xtt.platform.util.secret.HardWareUtil;

public class Validator extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = -6838081135391333931L;
	Logger log = LoggerFactory.getLogger(Validator.class);

	public void init() {
		if (System.getProperty("os.name").toLowerCase().startsWith("win") && !HardWareUtil.validateLicense()) {
			String[] arr = { "a", "b", "c", "d", "f", "g", "t", "k" };
			log.info(arr.toString().concat(System.getProperty("os.name")));
			System.exit(0);
		}
	}
}