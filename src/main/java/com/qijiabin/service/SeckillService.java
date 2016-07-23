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
 * �� �ڣ�2016��7��23�� ����6:18:33
 * �� �ߣ�jackson
 * �� ����1.0.0
 * ��˵����
 * TODO
 * ========================================================
 * �޶�����     �޶���    ����
 */
public interface SeckillService {

	/**
	 * ��ѯ������ɱ��Ʒ
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * ��ѯ������ɱ��Ʒ
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * ��ɱ����ʱ�����ɱ�ӿڵ�ַ��
	 * �������ϵͳʱ��
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * ִ����ɱ
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
	
}
