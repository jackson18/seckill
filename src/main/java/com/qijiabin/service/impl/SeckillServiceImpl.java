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
 * �� �ڣ�2016��7��23�� ����9:05:53
 * �� �ߣ�jackson
 * �� ����1.0.0
 * ��˵����
 * TODO
 * ========================================================
 * �޶�����     �޶���    ����
 */
@Service
public class SeckillServiceImpl implements SeckillService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SeckillServiceImpl.class);
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	// md5��ֵ
	private final String slat = "dsafeqfewkdsakdsfe*(3@#��%����";

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
	 * ʹ��ע���������
	 * ���񷽷�Ӧ�����̣ܶ���Ҫ����rpc��http����
	 * �������еķ�������Ҫ������ֻ��һ���޸Ĳ�����ֻ����������Ҫ�������
	 */
	@Transactional
	@Override
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		try {
			// �����
			int updateCount = seckillDao.reduceNumber(seckillId, new Date());
			if (updateCount <= 0) {
				// ����ʧ��, ��ɱ����
				throw new SeckillCloseException("seckill is closed");
			} else {
				// �� ¼������Ϊ
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				if (insertCount <= 0) {
					// �ظ���ɱ
					throw new RepeatKillException("seckill repeated");
				} else {
					// ��ɱ�ɹ�
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
			// ���б������쳣��ת��Ϊ�������쳣��spring��roll back
			throw new SeckillException("seckill inner error: " + e.getMessage());
		}
	}
	
	private String getMD5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
