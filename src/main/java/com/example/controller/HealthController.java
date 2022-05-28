package com.example.controller;

import com.example.infrastructure.common.R;
import com.example.service.ZhiHuSpiderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leonard
 * @date 2022/5/25
 * @Description 探针服务
 */

@Slf4j
@Api(tags = "探针服务")
@RestController
public class HealthController {

    @GetMapping(value = "/health")
    @ApiOperation(value = "探针")
    @ResponseBody
    public R health(){
        return new R<>("surge is health");
    }
}
