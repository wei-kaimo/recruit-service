package com.system.recruit.common.utils;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/1 17:28
 */
public class FileBean {

    private String filepath;

    private long lastModified;

    private String content;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
