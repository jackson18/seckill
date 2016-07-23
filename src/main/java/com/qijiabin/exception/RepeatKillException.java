package com.qijiabin.exception;

/**
 * ========================================================
 * 日 期：2016年7月23日 上午6:33:50
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：重复秒杀异常
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
@SuppressWarnings("serial")
public class RepeatKillException extends SeckillException {
	
	public RepeatKillException(String message) {
		super(message);
	}
	
	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

}
