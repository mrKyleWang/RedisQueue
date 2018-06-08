package top.kylewang.redismq.handler;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author KyleWang
 * @version 1.0
 * @description
 * @date 2018年06月07日
 */
public class Receiver implements Runnable {

	private RedisTemplate<String, String> redisTemplate;

	Receiver(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void run() {
		while (true) {
			redisTemplate.execute((RedisCallback<String>) redisConnection -> {
				byte[] keyByte = redisTemplate.getStringSerializer().serialize("tag");
				if (redisConnection.exists(keyByte)) {
					List<byte[]> bytes = redisConnection.bLPop(0, keyByte);
					if (bytes == null) {
						return null;
					}
					for (byte[] aByte : bytes) {
						String value = redisTemplate.getStringSerializer().deserialize(aByte);
						System.out.println(value);
					}
				}
				return null;
			});
		}
	}
}
