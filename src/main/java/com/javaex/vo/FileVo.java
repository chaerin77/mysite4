package com.javaex.vo;

public class FileVo {
	
	private int no;
	private int user_no;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private String fileSize;
	
	
	
	public FileVo() {
		
	}
	
	
	
	public FileVo(String filePath, String orgName, String saveName, String fileSize) {
		super();
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}



	public FileVo(int no, int user_no, String content, String filePath, String orgName, String saveName,
			String fileSize) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}

	
	
}
