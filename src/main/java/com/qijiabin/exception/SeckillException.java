package com.qijiabin.exception;

/**
 * ========================================================
 * 日 期：2016年7月23日 上午6:33:50
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：秒杀相关异常
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
@SuppressWarnings("serial")
public class SeckillException extends RuntimeException {
	
	public SeckillException(String message) {
		super(message);
	}
	
	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

}
