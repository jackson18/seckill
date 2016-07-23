package com.qijiabin.service;

import java.util.List;

import com.qijiabin.dto.Exposer;
import com.qijiabin.dto.SeckillExecution;
import com.qijiabin.entity.Seckill;
import com.qijiabin.exception.RepeatKillException;
import com.qijiabin.exception.SeckillCloseException;
import com.qijiabin.exception.SeckillException;

/**
 * ========================================================
 * 日 期：2016年7月23日 上午6:18:33
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public interface SeckillService {

	/**
	 * 查询所有秒杀商品
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个秒杀商品
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 秒杀开启时输出秒杀接口地址，
	 * 否则输出系统时间
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
	
}
