package com.qijiabin.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qijiabin.entity.Seckill;

/**
 * ========================================================
 * �� �ڣ�2016��7��23�� ����4:49:19
 * �� �ߣ�jackson
 * �� ����1.0.0
 * ��˵��������spring ��junit���ϣ�junit����ʱ����spring ioc����
 * TODO
 * ========================================================
 * �޶�����     �޶���    ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
	
	@Resource
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber() {
		int count = seckillDao.reduceNumber(1000, new Date());
		System.out.println(count);
	}
	
	@Test
	public void testQueryById() {
		long id = 1000;
		Seckill entity = seckillDao.queryById(id);
		if (entity != null) {
			System.out.println(entity);
		}
	}
	
	@Test
	public void testQueryAll() {
		List<Seckill> list = seckillDao.queryAll(0, 100);
		for (Seckill entity : list) {
			System.out.println(entity);
		}
	}
	
}
