package com.qijiabin.exception;

/**
 * ========================================================
 * �� �ڣ�2016��7��23�� ����6:33:50
 * �� �ߣ�jackson
 * �� ����1.0.0
 * ��˵������ɱ�ر��쳣
 * TODO
 * ========================================================
 * �޶�����     �޶���    ����
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
