package com.example.infrastructure.schedule;

import cn.hutool.core.util.StrUtil;
import com.example.dataobject.bo.ZhiHuUserBean;
import com.example.infrastructure.util.BloomFilter;
import com.example.infrastructure.util.SpiderUrlQueue;
import com.example.infrastructure.util.ThreadPoolManager;
import com.example.service.ZhiHuSpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @author leonard
 * @date 2022/5/27
 * @Description TODO
 */
@Slf4j
@Component
public class ZhiHuSpiderUserJob {

    @Autowired
    ZhiHuSpiderService zhiHuSpiderService;

    private BlockingQueue<String> blockingQueue = SpiderUrlQueue.get();

    //使用BloomFilter算法来去重
    private BloomFilter filter = new BloomFilter();

    private static CountDownLatch countDownLatch = new CountDownLatch(3);



    //5分钟执行一次
    @Scheduled(cron = "0 0/5 * * * *")
    public void cron2Db() throws InterruptedException {

        if (blockingQueue.isEmpty()) {
            log.info("无待爬url");
            return;
        }
        List<ZhiHuUserBean> zhiHuUserBeans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Runnable command = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (blockingQueue.isEmpty()) {
                            countDownLatch.countDown();
                            break;
                        }
                        String url = null;
                        try {
                            url = blockingQueue.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!filter.contains(url) && StrUtil.isNotEmpty(url)) {
                            filter.add(url);
                            ZhiHuUserBean zhiHuUserBean = zhiHuSpiderService.spiderZhiHuBean(url);
                            zhiHuUserBeans.add(zhiHuUserBean);
                        }

                    }
                }
            };
            ThreadPoolManager.THREAD_POOL_EXECUTOR.execute(command);
        }

        countDownLatch.await();
        //todo TODB

    }

}
