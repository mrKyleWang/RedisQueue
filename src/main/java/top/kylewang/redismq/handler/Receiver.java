package top.kylewang.redismq.handler;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author KyleWang
 * @version 1.0
 * @description
 * @date 2018年06月07日
 */
public class Receiver implements Runnable {

	private RedisTemplate<String, String> redisTemplate;

	Receiver(RedisTemplate<String, String> redisTemplate){
	    this.redisTemplate = redisTemplate;
    }

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("-------------" + Thread.currentThread().getName() + "-----run--------");
			String tag = redisTemplate.opsForList().leftPop("tag");
			if (tag != null) {
				System.out.println(tag);
			}
		}
	}
}
