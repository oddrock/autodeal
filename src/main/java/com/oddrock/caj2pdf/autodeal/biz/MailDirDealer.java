package com.oddrock.caj2pdf.autodeal.biz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 处理邮件文件夹的处理器
@Component("mailDirDealer")
public class MailDirDealer {
	private static Logger logger = Logger.getLogger(MailDirDealer.class);
	@Value("${qqmail.savefolder}") 
	private String savefolder;
	// 是否正在处理邮件的信号灯
	private volatile boolean doing = false;
	public synchronized boolean isDoing() {
		return doing;
	}
	public synchronized void setDoing(boolean doing) {
		this.doing = doing;
	}
	public void doOneMailDir(){
		if(isDoing()) {
			logger.warn("正在处理邮件，不重复处理......");
			return;
		}else{
			setDoing(true);
		}
		
		setDoing(false);
	}
}
