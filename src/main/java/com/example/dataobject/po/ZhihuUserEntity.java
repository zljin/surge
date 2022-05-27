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

	/**
	 * 
	 */
	@TableId
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
	private Integer gender;
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
	private String answercount;
	/**
	 * 
	 */
	private String articlescount;
	/**
	 * 
	 */
	private String voteupcount;
	/**
	 * 
	 */
	private String thankedcount;
	/**
	 * 
	 */
	private String followingnum;
	/**
	 * 
	 */
	private String followercount;
	/**
	 * 
	 */
	private Date createtime;
	/**
	 * 
	 */
	private String ext;

}
