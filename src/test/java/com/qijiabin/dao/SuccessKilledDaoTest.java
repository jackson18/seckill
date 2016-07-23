package com.qijiabin.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qijiabin.entity.SuccessKilled;

/**
 * ========================================================
 * 日 期：2016年7月23日 上午4:49:19
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：配置spring 和junit整合，junit启动时加载spring ioc容器
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
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
