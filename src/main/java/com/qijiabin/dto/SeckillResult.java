package com.qijiabin.dto;

/**
 * ========================================================
 * 日 期：2016年7月23日 下午4:29:03
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：封装json结果
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 * @param <T>
 */
public class SeckillResult<T> {

	private boolean success;
	
	private T data;
	
	private String error;

	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public SeckillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
