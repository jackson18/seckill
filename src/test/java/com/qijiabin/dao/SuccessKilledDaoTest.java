package com.qijiabin.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qijiabin.entity.SuccessKilled;

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
public class SuccessKilledDaoTest {
	
	@Resource
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled() {
		long id = 1001;
		long phone = 18211061588L;
		int count = successKilledDao.insertSuccessKilled(id, phone);
		System.out.println(count);
	}
	
	@Test
	public void testQueryByIdWithSeckill() {
		int id = 1001;
		long phone = 18211061588L;
		SuccessKilled entity = successKilledDao.queryByIdWithSeckill(id, phone);
		if (entity != null) {
			System.out.println(entity);
			System.out.println(entity.getSeckill());
		}
	}

}
