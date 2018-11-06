package com.meng.tencos.bean;


public class FileItem {
    private int fileIconRes;
    private String fileName;
	private long fileSize;
    //创建的时间字符串
    private String fileTime;
    //创建的时间戳
    private long ctime;
    //记录类型 文件夹：1；文件：0
    private int type;
    private String url;
    private boolean isChecked;

	public void setFileSize(long fileSize){
		this.fileSize=fileSize;
	}

	public long getFileSize(){
		return fileSize;
	}

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public FileItem(int fileIconRes, String fileName, long fileSize,String fileTime, long ctime, int type, String url) {
        this.fileIconRes = fileIconRes;
		this.fileSize=fileSize;
        this.fileName = fileName;
        this.fileTime = fileTime;
        this.ctime = ctime;
        this.type = type;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDownloadUrl(String url) {
        this.url = url;
    }

    public int getFileIconRes() {
        return fileIconRes;
    }

    public void setFileIconRes(int fileIconRes) {
        this.fileIconRes = fileIconRes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
