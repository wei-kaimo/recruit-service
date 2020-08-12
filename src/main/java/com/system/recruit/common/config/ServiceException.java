package com.system.recruit.common.config;

import org.apache.commons.lang.StringUtils;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”
 */
public class ServiceException extends Exception {
    /**
	 *
	 */
	private static final long serialVersionUID = -3399122496247464866L;

	public ServiceException() {
    }

    public ServiceException(String message, String...args) {
        super(replaceArgs(message, args));
    }

    public ServiceException(String message, Throwable cause, String...args) {
        super(replaceArgs(message, args), cause);
    }

    private static String replaceArgs(String src, String[] args) {
    		if (StringUtils.isEmpty(src)) {
    			for (int i = 1; i <= args.length; i++) {
    				src = src.replace("${" + i + "}", args[i - 1]);
    			}
    		}
    		return src;
    }
}
