package com.oddrock.caj2pdf.autodeal;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oddrock.caj2pdf.autodeal.biz.MailDirDealer;
import com.oddrock.caj2pdf.autodeal.biz.QQMailRcvr;

@Component("plugins4Mail")
public class Plugins4Mail {
	@Autowired
	private QQMailRcvr qqMailRcvr;
	@Autowired
	private MailDirDealer mailDirDealer;
	
	public void rcv(Exchange exchange, String payload) throws Exception{
		qqMailRcvr.downloadAttach();
	}
	
	public void domail(Exchange exchange, String payload) throws Exception{
		mailDirDealer.doOneMailDir();
	}
	
	public String test(Exchange exchange, String payload){
		return "Test success!";
	}
}
