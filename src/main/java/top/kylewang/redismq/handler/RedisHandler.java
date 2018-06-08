package top.kylewang.redismq.handler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author KyleWang
 * @version 1.0
 * @description
 * @date 2018年06月07日
 */
@Component
public class RedisHandler {

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	public void startListeneRedis() {
		Receiver receiver = new Receiver(redisTemplate);
		Thread thread = new Thread(receiver);
		thread.run();
	}
}
