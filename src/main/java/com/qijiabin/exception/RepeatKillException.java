package com.qijiabin.exception;

/**
 * ========================================================
 * �� �ڣ�2016��7��23�� ����6:33:50
 * �� �ߣ�jackson
 * �� ����1.0.0
 * ��˵�����ظ���ɱ�쳣
 * TODO
 * ========================================================
 * �޶�����     �޶���    ����
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
