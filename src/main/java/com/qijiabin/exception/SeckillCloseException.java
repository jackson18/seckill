package com.qijiabin.exception;

/**
 * ========================================================
 * 日 期：2016年7月23日 上午6:33:50
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：秒杀关闭异常
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
@SuppressWarnings("serial")
public class SeckillCloseException extends SeckillException {
	
	public SeckillCloseException(String message) {
		super(message);
	}
	
	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

}
