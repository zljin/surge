package com.example.infrastructure.schedule;

import com.example.infrastructure.util.RedisLock;
import com.example.infrastructure.util.ThreadPoolManager;
import com.example.service.ZhiHuSpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author leonard
 * @date 2022/5/27
 * @Description TODO
 */
@Slf4j
@Component
public class ZhiHuSpiderUserJob {

    @Autowired
    private ZhiHuSpiderService zhiHuSpiderService;

    @Value("${zhihu.spiderjob.enabled:false}")
    private boolean enabled;

    @Resource(name = "redisCommonTemplate")
    private RedisTemplate<String, Object> redisTemplate;


    //5分钟执行一次
    @Scheduled(cron = "0 0/2 * * * *")
    public void cron2Db(){
        if (!enabled) {
            return;
        }
        //分布式锁
        try (RedisLock redisLock = new RedisLock(redisTemplate, "zhihu:url:cron:lock", 30)) {
            if (redisLock.getLock()) {

                log.info("cronDB ...");
                try {
                    zhiHuSpiderService.consumerUserUrl();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                //开了一个监听线程，当爬虫线程池中线程出现异常中断时，新开线程加入线程池中，保证线程池数量稳定在4个
                Thread listenerThread = new Thread(() -> {
                    while (true) {
                        if (ThreadPoolManager.THREAD_POOL_EXECUTOR.getActiveCount() < 4) {
                            try {
                                zhiHuSpiderService.consumerUserUrl();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                ThreadPoolManager.THREAD_POOL_EXECUTOR.execute(listenerThread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
