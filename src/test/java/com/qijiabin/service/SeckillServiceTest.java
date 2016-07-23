package com.qijiabin.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qijiabin.dto.Exposer;
import com.qijiabin.dto.SeckillExecution;
import com.qijiabin.entity.Seckill;
import com.qijiabin.exception.RepeatKillException;
import com.qijiabin.exception.SeckillCloseException;
import com.qijiabin.exception.SeckillException;

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
@ContextConfiguration({
	"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"
})
public class SeckillServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SeckillServiceTest.class);
	
	@Resource
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		LOGGER.info("list={}", list);
	}
	
	@Test
	public void testGetById() {
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		LOGGER.info("seckill={}", seckill);
	}
	
	@Test
	public void testSeckillLogic() {
		long id = 1000;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		LOGGER.info("exposer={}", exposer);
		
		if (exposer.isExposed()) {
			long phone = 18211061588L;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
				LOGGER.info("result={}", seckillExecution);
			} catch (RepeatKillException e) {
				e.printStackTrace();
			} catch (SeckillCloseException e) {
				e.printStackTrace();
			} catch (SeckillException e) {
				e.printStackTrace();
			}
		} else {
			// ��ɱδ����
			LOGGER.warn("exposer={}", exposer);
		}
	}
	
}
