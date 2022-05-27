package com.example.service;

import com.example.dataobject.bo.ZhiHuUserBean;
import com.example.infrastructure.common.R;

import java.util.List;

public interface ZhiHuSpiderService {


    R spiderUserMessage(List<String> urls) throws InterruptedException;

    ZhiHuUserBean spiderZhiHuBean(String url);




}
