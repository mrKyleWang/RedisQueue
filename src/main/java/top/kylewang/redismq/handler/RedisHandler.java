package top.kylewang.redismq.handler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Receiver receiver = new Receiver(redisTemplate);
        executor.execute(receiver);
    }
}
