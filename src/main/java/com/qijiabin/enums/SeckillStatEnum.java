package com.qijiabin.enums;

/**
 * ========================================================
 * 日 期：2016年7月23日 上午9:31:40
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：枚举表示常量
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public enum SeckillStatEnum {
	
	SUCCESS(1, "秒杀成功"),
	END(0, "秒杀结束"),
	REPEAT_KILL(-1, "重复秒杀"),
	INNER_ERROR(-2, "系统异常"),
	DATA_REWRITE(-3, "数据篡改");

	private int state;

	private String stateInfo;

	private SeckillStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static SeckillStatEnum stateOf(int index) {
		for (SeckillStatEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
