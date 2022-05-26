package com.example.service;

import com.example.infrastructure.common.R;

public interface ZhiHuSpiderService {


    R spiderUserMessage(String url);
    R spiderUserMessage(String url,String file);
    R spiderUserMessage(String url,int type);




}
