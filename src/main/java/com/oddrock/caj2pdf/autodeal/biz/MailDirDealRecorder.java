package com.oddrock.caj2pdf.autodeal.biz;

import java.io.File;
import java.io.IOException;

// 记录邮件目录的处理情况
public interface MailDirDealRecorder {
	public boolean isDone(File mailDir) throws IOException;		// 是否已处理
	public void setDone(File mailDir) throws IOException;		// 设为已处理
	public void setUnDone(File mailDir) throws IOException;		// 设为未处理
}
