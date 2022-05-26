package com.example.service.impl;

import com.example.dataobject.bo.ZhiHuUserBean;
import com.example.infrastructure.common.R;
import com.example.infrastructure.util.BloomFilter;
import com.example.infrastructure.util.JsoupUtils;
import com.example.service.ZhiHuSpiderService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * @author leonard
 * @date 2022/5/26
 * @Description TODO
 */
@Slf4j
@Service
public class ZhiHuSpiderServiceImpl implements ZhiHuSpiderService {

    //使用BloomFilter算法来去重
    private static BloomFilter filter = new BloomFilter();
    private static final String eurl = "https://www.zhihu.com/people/zhang-jia-wei";

    @Override
    public R spiderUserMessage(String url) {
        R result = new R();





        return result;
    }

    @Override
    public R spiderUserMessage(String url, String file) {
        return null;
    }

    @Override
    public R spiderUserMessage(String url,int type) {
        return null;
    }



    private ZhiHuUserBean spiderZhiHuBean(String url) {
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


    //传入用户url，获取他所关注人的url
    public static void addUserFollowingUrl(String userUrl) {
        int i = 1;
        while (true) {
            String userFollowingUrl = "";
            //知乎接口只能查到前三个关注人
            userFollowingUrl = userUrl + "/following?page=" + i;
            i++;
            Document userFollowingContent = JsoupUtils.getDocument(userFollowingUrl);
            Elements followingElements = userFollowingContent.select(".List-item");
            //判断当前页关注人数是否为0，是的话就跳出循环
            if (followingElements.size() != 0) {
                for (Element e : followingElements) {
                    String newUserUrl = e.select("a[href]").get(0).attr("href");
                    if (!filter.contains(newUserUrl)) {
                        //把获取到的地址加入阻塞队列
                        System.out.println("https:" + newUserUrl);
                        filter.add(newUserUrl);
                        addUserFollowingUrl("https:" + newUserUrl);
                    }
                }
            }
            if (followingElements.size() == 0) {
                break;
            }
        }

    }


}
