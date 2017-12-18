package com.oddrock.caj2pdf.autodeal.biz;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.oddrock.common.mail.AttachDownloadDirGenerator;
import com.oddrock.common.mail.PopMailRcvr;

@Component("qqMailRcvr")
public class QQMailRcvr {
	private static Logger logger = Logger.getLogger(QQMailRcvr.class);
	@Value("${qqmail.popserver}") 
	private String popServer;
	@Value("${qqmail.account}") 
	private String account;
	@Value("${qqmail.authcode}") 
	private String authcode;
	@Value("${qqmail.foldername}") 
	private String folderName;
	@Value("${qqmail.savefolder}") 
	private String savefolder;
	@Autowired
	private PopMailRcvr popMailRcvr;
	@Autowired
	private AttachDownloadDirGenerator attachDownloadDirGenerator;
	// 是否正在接收邮件的信号灯
	private volatile boolean rcving = false;
	public synchronized boolean isRcving() {
		return rcving;
	}
	public synchronized void setRcving(boolean rcving) {
		this.rcving = rcving;
	}
	public void downloadAttach() throws Exception{
		if(isRcving()) {
			logger.warn("正在接收邮件，不重复接收......");
			return;
		}else{
			setRcving(true);
		}
		new File(savefolder).mkdirs();
		popMailRcvr.rcvMail(popServer, account, authcode, folderName, true, savefolder, attachDownloadDirGenerator);
		setRcving(false);
	}
}
