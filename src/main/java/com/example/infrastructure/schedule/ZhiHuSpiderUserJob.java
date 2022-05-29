package com.example.infrastructure.schedule;

import com.example.infrastructure.util.RedisLock;
import com.example.service.ZhiHuSpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

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
    public void cron2Db() throws InterruptedException {
        try (RedisLock redisLock = new RedisLock(redisTemplate, "zhihu:url:cron:lock", 30)) {
            if (redisLock.getLock()) {
                if (!enabled) {
                    return;
                }
                log.info("cronDB ...");
                zhiHuSpiderService.consumerUserUrl();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
