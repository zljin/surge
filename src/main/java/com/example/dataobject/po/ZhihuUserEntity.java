package com.example.dataobject.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("zhihu_user")
public class ZhihuUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String gender;
	/**
	 * 
	 */
	private String business;
	/**
	 * 
	 */
	private String company;
	/**
	 * 
	 */
	private String position;
	/**
	 * 
	 */
	private String education;
	/**
	 * 
	 */
	private String major;
	/**
	 * 
	 */
	private String image;
	/**
	 * 
	 */
	private String answerCount;
	/**
	 * 
	 */
	private String articlesCount;
	/**
	 * 
	 */
	private String voteupCount;
	/**
	 * 
	 */
	private String thankedCount;
	/**
	 * 
	 */
	private String followingNum;
	/**
	 * 
	 */
	private String followerCount;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String ext;

}
