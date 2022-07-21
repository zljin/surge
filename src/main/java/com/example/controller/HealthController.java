package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@RestController
public class HealthController {

    @Value("${status:unavailable}")
    private String status;

    @GetMapping(value = "/health")
    @ResponseBody
    public R health() {
        String tip = "surge is health. ";
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            tip = tip+" IP: "+hostAddress +" Status: "+ status;
        } catch (UnknownHostException e) {
            log.info("error:" + e);
        }
        return new R<>(tip);
    }
}
