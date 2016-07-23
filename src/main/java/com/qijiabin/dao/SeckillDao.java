package com.qijiabin.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qijiabin.entity.Seckill;

/**
 * ========================================================
 * �� �ڣ�2016��7��21�� ����11:09:48
 * �� �ߣ�jackson
 * �� ����1.0.0
 * ��˵����
 * TODO
 * ========================================================
 * �޶�����     �޶���    ����
 */
public interface SeckillDao {

	/**
	 * �����
	 * @param seckillId
	 * @param killTime
	 * @return ����1��ʾ�ɹ�
	 */
	int reduceNumber(@Param("seckillId")long seckillId, @Param("killTime")Date killTime);
	
	/**
	 * ����id��ѯ��ɱ��Ʒ
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * ����ƫ������ѯ��ɱ��Ʒ�б�
	 * @param offet
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset")int offset, @Param("limit")int limit);
}
