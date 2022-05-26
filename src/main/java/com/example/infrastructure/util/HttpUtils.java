package com.example.infrastructure.util;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 给出一种代理ip解决方案
 * star: 每次解析url地址时，自动设置动态IP代理，否则会报429连接数量过多异常
 */
@Slf4j
public class HttpUtils {

    private static final String PROXY_FILE = "/static/proxyip.txt";

    public static void setProxyIp() {
        try {
            List<String> ipList = new ArrayList<>();
            BufferedReader proxyIpReader = new BufferedReader(new InputStreamReader(HttpUtils.class.getResourceAsStream(PROXY_FILE)));

            String ip = "";
            while ((ip = proxyIpReader.readLine()) != null) {
                ipList.add(ip);
            }

            Random random = new Random();
            int randomInt = random.nextInt(ipList.size());
            String ipport = ipList.get(randomInt);
            String proxyIp = ipport.substring(0, ipport.lastIndexOf(":"));
            String proxyPort = ipport.substring(ipport.lastIndexOf(":") + 1, ipport.length());

            System.setProperty("http.maxRedirects", "50");
            System.getProperties().setProperty("proxySet", "true");
            System.getProperties().setProperty("http.proxyHost", proxyIp);
            System.getProperties().setProperty("http.proxyPort", proxyPort);

            log.info("设置代理ip为：{} 端口号为：{}", proxyIp, proxyPort);
        } catch (Exception e) {
            log.info("重新设置代理ip");
            setProxyIp();
        }
    }
}
