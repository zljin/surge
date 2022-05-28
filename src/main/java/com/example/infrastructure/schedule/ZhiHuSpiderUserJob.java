package com.example.infrastructure.schedule;

import com.example.service.ZhiHuSpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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


    //5分钟执行一次
    @Scheduled(cron = "0 0/2 * * * *")
    public void cron2Db() throws InterruptedException {
        if (!enabled) {
            return;
        }
        zhiHuSpiderService.consumerUserUrl();
    }

}
