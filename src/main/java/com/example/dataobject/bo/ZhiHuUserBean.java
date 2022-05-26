package com.example.dataobject.bo;

import lombok.Data;
import lombok.ToString;

/**
 * 知乎用户bean
 *
 * @author leonard
 */
@Data
@ToString
public class ZhiHuUserBean {

    //url
    String url;//用户地址

    String name;// 用户姓名

    //gender:Male
    String gender;//性别

    String business;// 行业

    String company;// 公司

    String position;// 职位;

    String education;// 大学

    String major;// 专业

    //image
    String image;//头像

    //zhihu:answerCount
    String answerCount;// 回答数量

    //zhihu:articlesCount
    String articlesCount;//文章数量

    //zhihu:voteupCount
    String voteupCount;// 被赞同数次数

    //zhihu:thankedCount
    String thankedCount;// 被喜欢数次数

    String followingNum;//关注的人

    //zhihu:followerCount
    String followerCount;//关注者数量
}
