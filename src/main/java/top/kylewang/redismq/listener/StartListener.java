package top.kylewang.redismq.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import top.kylewang.redismq.handler.RedisHandler;

/**
 * @author KyleWang
 * @version 1.0
 * @description
 * @date 2018年06月07日
 */
@Component
public class StartListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private RedisHandler redisHandler;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ApplicationReadyEvent) {
			// spring容器启动后，开始启动线程池
            redisHandler.startListeneRedis();
		}
	}
}
