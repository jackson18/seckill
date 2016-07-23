package com.qijiabin.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qijiabin.dto.Exposer;
import com.qijiabin.dto.SeckillExecution;
import com.qijiabin.dto.SeckillResult;
import com.qijiabin.entity.Seckill;
import com.qijiabin.enums.SeckillStatEnum;
import com.qijiabin.exception.RepeatKillException;
import com.qijiabin.exception.SeckillCloseException;
import com.qijiabin.exception.SeckillException;
import com.qijiabin.service.SeckillService;

/**
 * ========================================================
 * 日 期：2016年7月23日 下午4:19:32
 * 作 者：jackson
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
@Controller
@RequestMapping("/")
public class SeckillController {
	
	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/{seckillId}/detail", method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill entity = seckillService.getById(seckillId);
		if (entity == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", entity);
		return "detail";
	}
	
	@RequestMapping(value="/{seckillId}/export", method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> export(@PathVariable Long seckillId) {
		SeckillResult<Exposer> result = null;;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			result = new SeckillResult<Exposer>(true, e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/{seckillId}/{md5}/execution", method=RequestMethod.POST,
					produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(
			@PathVariable("seckillId")Long seckillId,
			@PathVariable("md5")String md5,
			@CookieValue(value="killPhone", required=false)Long phone) {
		if (phone == null) {
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		try {
			SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (RepeatKillException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (SeckillCloseException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (SeckillException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
	}
	
	@RequestMapping(value="/time/now", method=RequestMethod.GET,
			produces = "application/json;charset=UTF-8")
	@ResponseBody
	public SeckillResult<Long> time() {
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
	
}

