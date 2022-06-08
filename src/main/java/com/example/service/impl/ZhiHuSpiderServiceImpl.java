package com.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.example.dataobject.bo.ZhiHuUserBean;
import com.example.dataobject.po.ZhihuUserEntity;
import com.example.infrastructure.common.R;
import com.example.infrastructure.constant.CommonConstant;
import com.example.infrastructure.util.BloomFilter;
import com.example.infrastructure.util.AtomicCountUtil;
import com.example.infrastructure.util.JsoupUtils;
import com.example.infrastructure.util.ThreadPoolManager;
import com.example.service.ZhiHuSpiderService;
import com.example.service.ZhihuUserService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author leonard
 * @date 2022/5/26
 * @Description https://zhuanlan.zhihu.com/p/24973518
 */
@Slf4j
@Service
public class ZhiHuSpiderServiceImpl implements ZhiHuSpiderService {

    @Resource(name = "redisCommonTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ZhihuUserService zhihuUserService;

    //使用BloomFilter算法来去重
    private BloomFilter filter = new BloomFilter();

    private static CountDownLatch countDownLatch = new CountDownLatch(3);


    @Override
    public R receiveUserUrl(List<String> urls) {
        R result = new R();
        int i = 0;
        for (; i < urls.size(); i++) {
            redisTemplate.opsForList()
                    .leftPush(CommonConstant.SPIDER_URL_QUEUE, urls.get(i));
            AtomicCountUtil.cal();
        }
        result.setMessage("已导入：" + i);
        return result;
    }

    @Override
    public void consumerUserUrl() throws InterruptedException {
        ListOperations<String, Object> ops = redisTemplate.opsForList();
        Long size = ops.size(CommonConstant.SPIDER_URL_QUEUE);
        if (size == null || size == 0) {
            return;
        }
        List<ZhihuUserEntity> zhihuUserEntities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Runnable command = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Long size = ops.size(CommonConstant.SPIDER_URL_QUEUE);
                        if (size == null || size == 0) {
                            countDownLatch.countDown();
                            break;
                        }
                        String url = (String) ops.rightPop(CommonConstant.SPIDER_URL_QUEUE);
                        if (!filter.contains(url) && StrUtil.isNotEmpty(url)) {
                            filter.add(url);
                            ZhiHuUserBean zhiHuUserBean = spiderZhiHuBean(url);
                            ZhihuUserEntity zhihuUserEntity = new ZhihuUserEntity();
                            BeanUtil.copyProperties(zhiHuUserBean, zhihuUserEntity);
                            zhihuUserEntity.setUrl(url);
                            zhihuUserEntity.setCreateTime(new Date());
                            zhihuUserEntities.add(zhihuUserEntity);
                            addUserFollowingUrl(url);
                        }
                    }
                }
            };
            ThreadPoolManager.THREAD_POOL_EXECUTOR.execute(command);
        }

        countDownLatch.await();
        try {
            boolean b = zhihuUserService.saveBatch(zhihuUserEntities);
            log.info("the result is :{}", b);
        } catch (Exception e) {
            log.info("the err is :{}", e.toString());
        }finally {
            log.info("AtomicCountUtil.get:{}",AtomicCountUtil.get());
            //0-1000内清空，重新递归
            AtomicCountUtil.clearZero();
        }
    }

    private void addUserFollowingUrl(String userUrl) {
        if(AtomicCountUtil.get()>1000){
            return;
        }
        int i = 1;
        String userFollowingUrl = "";
        //知乎接口只能查到前三个关注人
        userFollowingUrl = userUrl + "/following?page=" + i;
        Document userFollowingContent = JsoupUtils.getDocument(userFollowingUrl);
        Elements followingElements = userFollowingContent.select(".List-item");
        //判断当前页关注人数是否为0，是的话就跳出循环
        if (followingElements.size() != 0) {
            for (Element e : followingElements) {
                String newUserUrl = e.select("a[href]").get(0).attr("href");
                if (!filter.contains(newUserUrl)) {
                    //把获取到的地址加入阻塞队列
                    filter.add(newUserUrl);
                    log.info("https:" + newUserUrl);
                    redisTemplate.opsForList().leftPush(CommonConstant.SPIDER_URL_QUEUE, "https:" + newUserUrl);
                    AtomicCountUtil.cal();
                    addUserFollowingUrl("https:" + newUserUrl);
                }
            }
        }
    }


    private ZhiHuUserBean spiderZhiHuBean(String url) {
        ZhiHuUserBean zhiHuUserBean = new ZhiHuUserBean();
        Document userUrlContent = JsoupUtils.getDocument(url);
        String userContent = userUrlContent.text();

        zhiHuUserBean.setName("");
        zhiHuUserBean.setFollowingNum("");
        zhiHuUserBean.setUrl(url);
        //名称
        if (userContent.contains(".ProfileHeader-name")) {
            String name = userUrlContent.select(".ProfileHeader-name").first().text();
            zhiHuUserBean.setName(name);
        }
        //关注的人
        if (userContent.contains(".NumberBoard-itemValue")) {
            String followingNum = userUrlContent.select(".NumberBoard-itemValue").first().text();
            zhiHuUserBean.setFollowingNum(followingNum);
        }
        if(userContent.contains("meta[itemprop=gender]")){
            String gender = userUrlContent.select("meta[itemprop=gender]").first().attr("content");
            zhiHuUserBean.setGender(gender);
        }
        if(userContent.contains("meta[itemprop=image]")){
            String image = userUrlContent.select("meta[itemprop=image]").first().attr("content");
            zhiHuUserBean.setImage(image);
        }
        if(userContent.contains("meta[itemprop=zhihu:voteupCount]")){
            String voteupCount = userUrlContent.select("meta[itemprop=zhihu:voteupCount]").first().attr("content");
            zhiHuUserBean.setVoteupCount(voteupCount);
        }
        if(userContent.contains("meta[itemprop=zhihu:thankedCount]")){
            String thankedCount = userUrlContent.select("meta[itemprop=zhihu:thankedCount]").first().attr("content");
            zhiHuUserBean.setThankedCount(thankedCount);
        }
        if(userContent.contains("meta[itemprop=zhihu:followerCount]")){
            String followerCount = userUrlContent.select("meta[itemprop=zhihu:followerCount]").first().attr("content");
            zhiHuUserBean.setFollowerCount(followerCount);
        }
        if(userContent.contains("zhihu:answerCount")){
            //<meta itemprop="zhihu:answerCount" content="4142">
            String answerCount = userUrlContent.select("meta[itemprop=zhihu:answerCount]").first().attr("content");
            zhiHuUserBean.setAnswerCount(answerCount);
        }
        if(userContent.contains("meta[itemprop=zhihu:articlesCount]")){
            String articlesCount = userUrlContent.select("meta[itemprop=zhihu:articlesCount]").first().attr("content");
            zhiHuUserBean.setArticlesCount(articlesCount);
        }
        //行业 公司 职位
        int size = userUrlContent.select(".ProfileHeader-infoItem").size();
        if (size == 2) {
            String string1 = userUrlContent.select(".ProfileHeader-infoItem").first().text();
            if (string1 != null && string1 != "") {
                String[] a = string1.split(" ");
                //行业
                for (int i = 0; i < a.length; i++) {
                    if (a.length > 0) {
                        zhiHuUserBean.setBusiness(a[0]);
                    }
                    //公司
                    if (a.length > 1) {
                        zhiHuUserBean.setCompany(a[1]);
                    }
                    //职位
                    if (a.length > 2) {
                        zhiHuUserBean.setPosition(a[2]);
                    }
                }
            }
            String string2 = userUrlContent.select(".ProfileHeader-infoItem").get(1).text();
            if (string2 != null && string2 != "") {
                String[] a = string2.split(" ");
                //学校
                if (a.length > 0) {
                    zhiHuUserBean.setEducation(a[0]);
                }
                //专业
                if (a.length > 1) {
                    zhiHuUserBean.setMajor(a[1]);
                }
            }
        }
        return zhiHuUserBean;
    }


}
