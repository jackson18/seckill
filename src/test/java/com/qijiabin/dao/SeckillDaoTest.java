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
