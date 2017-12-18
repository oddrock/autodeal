package com.oddrock.caj2pdf.autodeal.biz;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.oddrock.common.file.FileUtils;

// 用文件形式实现MailDirDealRecorder
@Component("mailDirDealRecorder")
public class FileMailDirDealRecorder implements MailDirDealRecorder {
	@Value("${qqmail.maildirdealrecord.filepath}") 
	private String recordFilePath;
	@Value("${qqmail.maildirdealrecord.separator}") 
	private String separator;
	private Set<String> doneMailDirPathSet;
	
	// 从文件中将记录初始化到内存
	@PostConstruct
	public void init() throws IOException{
		doneMailDirPathSet = new HashSet<String>();
		File file = new File(recordFilePath);
		FileUtils.mkDirOfParent(file);
		String content = FileUtils.readFileContentToStrExt(file.getCanonicalPath());
		String[] maildirs = content.split(separator);
		for(String mailDir : maildirs){
			doneMailDirPathSet.add(mailDir);
		}
	}
	
	// 将内存刷新到文件中
	@PreDestroy
	private void flush() throws IOException{
		StringBuffer content = new StringBuffer();
		boolean first = true;
		for(String mailDir : doneMailDirPathSet){
			if(first){
				content.append(separator);
				first = false;
			}
			content.append(mailDir);
		}
		FileUtils.writeToFile(recordFilePath, content.toString(), false);
	}
	
	@Override
	public boolean isDone(File mailDir) throws IOException {
		return doneMailDirPathSet.contains(mailDir.getCanonicalPath());
	}

	@Override
	public void setDone(File mailDir) throws IOException {
		doneMailDirPathSet.add(mailDir.getCanonicalPath());
		flush();
	}

	@Override
	public void setUnDone(File mailDir) throws IOException {
		if(isDone(mailDir)){
			doneMailDirPathSet.remove(mailDir);
			flush();
		}
	}
}
