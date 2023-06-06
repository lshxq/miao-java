package stian.miao.rest;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import stian.miao.entity.AccessEntity;
import stian.miao.entity.ScoreEntity;
import stian.miao.mapper.ScoreMapper;

@RestController
@Slf4j
@RequestMapping("access")
public class AccessService {
	
	@Autowired
	private ScoreMapper mapper;
	
	
	@GetMapping()
	int newAccess(HttpServletRequest req) {
		AccessEntity en = new AccessEntity();
	
		
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
		
		en.setIp(ip);
		en.setAccess(new Date());
		
		int updated = mapper.newAccess(en);
		
		return updated;
	}
	
	@GetMapping("top10")
	List<ScoreEntity> getTop10() {
		return mapper.getTop10();
	}
}
