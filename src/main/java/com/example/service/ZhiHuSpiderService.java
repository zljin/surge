package com.example.service;

import com.example.infrastructure.common.R;

import java.util.List;

public interface ZhiHuSpiderService {


    R receiveUserUrl(List<String> urls);

    void consumerUserUrl() throws InterruptedException;



}
