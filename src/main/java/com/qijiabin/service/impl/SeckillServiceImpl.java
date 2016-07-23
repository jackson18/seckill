package com.qijiabin.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.qijiabin.dao.SeckillDao;
import com.qijiabin.dao.SuccessKilledDao;
import com.qijiabin.dto.Exposer;
import com.qijiabin.dto.SeckillExecution;
import com.qijiabin.entity.Seckill;
import com.qijiabin.entity.SuccessKilled;
import com.qijiabin.enums.SeckillStatEnum;
import com.qijiabin.exception.RepeatKillException;
import com.qijiabin.exception.SeckillCloseException;
import com.qijiabin.exception.SeckillException;
import com.qijiabin.service.SeckillService;

/**
 * ========================================================
 * 日 期：2016年7月23日 上午9:05:53
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
@Service
public class SeckillServiceImpl implements SeckillService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SeckillServiceImpl.class);
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	// md5盐值
	private final String slat = "dsafeqfewkdsakdsfe*(3@#￥%……";

	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 10);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		if ( seckill == null) {
			return new Exposer(false, seckillId);
		}
		
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date now = new Date();
		if (now.before(startTime) || now.after(endTime)) {
			return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
		}
		
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	/**
	 * 使用注解控制事务
	 * 事务方法应尽可能短，不要加入rpc或http请求
	 * 不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	@Transactional
	@Override
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		try {
			// 减库存
			int updateCount = seckillDao.reduceNumber(seckillId, new Date());
			if (updateCount <= 0) {
				// 更新失败, 秒杀结束
				throw new SeckillCloseException("seckill is closed");
			} else {
				// 记 录购买行为
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				if (insertCount <= 0) {
					// 重复秒杀
					throw new RepeatKillException("seckill repeated");
				} else {
					// 秒杀成功
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e) {
			throw e;
		} catch (RepeatKillException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			// 所有编译期异常，转化为运行期异常，spring会roll back
			throw new SeckillException("seckill inner error: " + e.getMessage());
		}
	}
	
	private String getMD5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
