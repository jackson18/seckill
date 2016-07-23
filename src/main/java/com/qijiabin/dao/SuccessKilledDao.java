package com.qijiabin.dao;

import org.apache.ibatis.annotations.Param;

import com.qijiabin.entity.SuccessKilled;

/**
 * ========================================================
 * �� �ڣ�2016��7��21�� ����11:09:55
 * �� �ߣ�jackson
 * �� ����1.0.0
 * ��˵����
 * TODO
 * ========================================================
 * �޶�����     �޶���    ����
 */
public interface SuccessKilledDao {

	/**
	 * ���빺����ϸ���ɹ����ظ�
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);
	
	/**
	 * ����id��ѯSuccessKilled��Я����ɱ��Ʒ����ʵ��
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);
}
