package com.example.infrastructure.schedule;

import cn.hutool.core.util.StrUtil;
import com.example.dataobject.bo.ZhiHuUserBean;
import com.example.dataobject.po.ZhihuUserEntity;
import com.example.infrastructure.util.BloomFilter;
import com.example.infrastructure.util.SnowFlakeUtil;
import com.example.infrastructure.util.SpiderUrlQueue;
import com.example.infrastructure.util.ThreadPoolManager;
import com.example.service.ZhiHuSpiderService;
import com.example.service.ZhihuUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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

    @Resource
    private ZhihuUserService zhihuUserService;

    //使用BloomFilter算法来去重
    private BloomFilter filter = new BloomFilter();

    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    @Value("${zhihu.spiderjob.enabled:false}")
    private boolean enabled;

    //5分钟执行一次
    @Scheduled(cron = "0 0/5 * * * *")
    public void cron2Db() throws InterruptedException {

        if(!enabled){
            return;
        }

        if (SpiderUrlQueue.get().isEmpty()) {
            log.info("无待爬url");
            return;
        }
        List<ZhihuUserEntity> zhihuUserEntities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Runnable command = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (SpiderUrlQueue.get().isEmpty()) {
                            countDownLatch.countDown();
                            break;
                        }
                        String url = null;
                        try {
                            url = SpiderUrlQueue.get().take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (!filter.contains(url) && StrUtil.isNotEmpty(url)) {
                            filter.add(url);
                            ZhiHuUserBean zhiHuUserBean = zhiHuSpiderService.spiderZhiHuBean(url);
                            ZhihuUserEntity zhihuUserEntity = new ZhihuUserEntity();
                            BeanUtils.copyProperties(zhiHuUserBean,zhihuUserEntity);
                            zhihuUserEntity.setId(SnowFlakeUtil.getInstance().nextId());
                        }

                    }
                }
            };
            ThreadPoolManager.THREAD_POOL_EXECUTOR.execute(command);
        }

        countDownLatch.await();
        zhihuUserService.saveBatch(zhihuUserEntities);
    }

}
