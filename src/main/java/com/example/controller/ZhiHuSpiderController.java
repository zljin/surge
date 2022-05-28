package com.example.controller;

import com.example.infrastructure.common.R;
import com.example.service.ZhiHuSpiderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author leonard
 * @date 2022/5/28
 * @Description TODO
 */
@Slf4j
@Api(tags = "探针服务")
@RestController
public class ZhiHuSpiderController {

    @Autowired
    private ZhiHuSpiderService zhiHuSpiderService;

    @PostMapping(value = "/zhihu/receive")
    @ApiOperation(value = "探针")
    @ResponseBody
    public R receive(@RequestBody List<String> urls){
        zhiHuSpiderService.receiveUserUrl(urls);
        return new R<>("surge is health");
    }
}
