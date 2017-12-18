package com.oddrock.caj2pdf.autodeal;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("plugins4Mail")
public class Plugins4Mail {
	private static Logger logger = Logger.getLogger(Plugins4Mail.class);
	
	public void rcv(Exchange exchange, String payload){
		logger.warn("已开始运行Plugins4Mail...");
		logger.warn("已结束运行Plugins4Mail");
	}
	
	public String test(Exchange exchange, String payload){
		logger.warn("has enter test method");
		return "Test success!";
	}
}
