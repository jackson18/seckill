package com.qijiabin.exception;

/**
 * ========================================================
 * �� �ڣ�2016��7��23�� ����6:33:50
 * �� �ߣ�jackson
 * �� ����1.0.0
 * ��˵������ɱ����쳣
 * TODO
 * ========================================================
 * �޶�����     �޶���    ����
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
