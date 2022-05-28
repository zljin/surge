package com.example.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Slf4j
public class JsoupUtils {
	
	/**
	 * 通过地址得到document对象
	 * @param url
	 */
	public static Document getDocument(String url) {
			try {
				Document document = Jsoup.connect(url).timeout(100000).get();

				if(document == null || document.toString().trim().equals("")) {// 表示ip被拦截或者其他情况
					System.out.println("出现ip被拦截或者其他情况");
					HttpUtils.setProxyIp();
					getDocument(url);
				}
				return document;
			} catch (Exception e) { // 链接超时等其他情况
				log.info("JsoupUtils err:{}",e.toString());
				HttpUtils.setProxyIp();// 换代理ip
				getDocument(url);// 继续爬取网页
			}
			return getDocument(url);
		}
	
	
	public static void main(String[] args) {
		String url = "https://www.zhihu.com/people/zou-ling-jin-80";
		System.out.println(getDocument(url));
	}
}
