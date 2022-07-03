package com.example.controller;

import com.example.infrastructure.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author leonard
 * @date 2022/5/25
 * @Description 探针服务
 */

@Slf4j
@Api(tags = "探针服务")
@RestController
public class HealthController {

    @Value("${status:unavailable}")
    private String status;

    @GetMapping(value = "/health")
    @ApiOperation(value = "探针")
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
