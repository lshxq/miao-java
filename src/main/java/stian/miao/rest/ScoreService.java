package stian.miao.rest;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import stian.miao.entity.ScoreEntity;
import stian.miao.mapper.ScoreMapper;

@RestController
@Slf4j
@RequestMapping("score")
public class ScoreService {
	
	@Autowired
	private ScoreMapper mapper;
	
	
	@PostMapping()
	int saveRecord(@RequestBody ScoreEntity score, HttpServletRequest req) {
		if(score.getUserId() == null || score.getUserId().length() == 0) {
			return -1; // 没有userId
		}
		
		score.setUpdated(new Date());
		
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("X-Forwarded-for");
		}
		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = req.getRemoteAddr();
		}
		
		score.setIp(ip);
		
		int updated = mapper.updateRecord(score);
		
		if (updated == 0) {
			updated = mapper.insertRecord(score);
		}
		return updated;
	}
	
	@GetMapping("top10")
	List<ScoreEntity> getTop10() {
		return mapper.getTop10();
	}
}
