package com.qijiabin.dto;

import com.qijiabin.entity.SuccessKilled;
import com.qijiabin.enums.SeckillStatEnum;

/**
 * ========================================================
 * 日 期：2016年7月23日 上午6:28:34
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：封装秒杀执行后的结果
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public class SeckillExecution {

	private long seckillId;
	// 秒杀执行结果状态
	private int state;
	// 状态表示
	private String stateInfo;
	// 秒杀成功对象
	private SuccessKilled successKilled;
	
	public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
		super();
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
	}

	public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
		this.successKilled = successKilled;
	}
	
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successKilled=" + successKilled + "]";
	}
	
}
