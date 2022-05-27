package com.example.service.impl;

import com.example.dataobject.bo.ZhiHuUserBean;
import com.example.infrastructure.common.R;
import com.example.infrastructure.util.JsoupUtils;
import com.example.infrastructure.util.SpiderUrlQueue;
import com.example.service.ZhiHuSpiderService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author leonard
 * @date 2022/5/26
 * @Description TODO
 */
@Slf4j
@Service
public class ZhiHuSpiderServiceImpl implements ZhiHuSpiderService {

    @Override
    public R spiderUserMessage(List<String> urls) {
        R result = new R();
        int i = 0;
        for (; i < urls.size(); i++) {
            try {
                SpiderUrlQueue.get().put(urls.get(i));
            } catch (InterruptedException e) {
                log.info("SpiderUrlQueue is full.");
            }
        }
        result.setMessage("已导入：" + i);
        return result;
    }

    public ZhiHuUserBean spiderZhiHuBean(String url) {
        ZhiHuUserBean zhiHuUserBean = new ZhiHuUserBean();
        Document userUrlContent = JsoupUtils.getDocument(url);
        //String userContent = userUrlContent.text();

        //名称
        String name = userUrlContent.select(".ProfileHeader-name").first().text();
        //关注的人
        String followingNum = userUrlContent.select(".NumberBoard-itemValue").first().text();
        String gender = userUrlContent.select("meta[itemprop=gender]").first().attr("content");
        String image = userUrlContent.select("meta[itemprop=image]").first().attr("content");
        String voteupCount = userUrlContent.select("meta[itemprop=zhihu:voteupCount]").first().attr("content");
        String thankedCount = userUrlContent.select("meta[itemprop=zhihu:thankedCount]").first().attr("content");
        String followerCount = userUrlContent.select("meta[itemprop=zhihu:followerCount]").first().attr("content");
        //<meta itemprop="zhihu:answerCount" content="4142">
        String answerCount = userUrlContent.select("meta[itemprop=zhihu:answerCount]").first().attr("content");
        String articlesCount = userUrlContent.select("meta[itemprop=zhihu:articlesCount]").first().attr("content");

        zhiHuUserBean.setName(name);
        zhiHuUserBean.setFollowingNum(followingNum);
        zhiHuUserBean.setGender(gender);
        zhiHuUserBean.setImage(image);
        zhiHuUserBean.setVoteupCount(voteupCount);
        zhiHuUserBean.setThankedCount(thankedCount);
        zhiHuUserBean.setFollowerCount(followerCount);
        zhiHuUserBean.setAnswerCount(answerCount);
        zhiHuUserBean.setArticlesCount(articlesCount);

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
