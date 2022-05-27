package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dataobject.po.ZhihuUserEntity;
import com.example.mapper.ZhihuUserDao;
import com.example.service.ZhihuUserService;
import org.springframework.stereotype.Service;



@Service
public class ZhihuUserServiceImpl extends ServiceImpl<ZhihuUserDao, ZhihuUserEntity> implements ZhihuUserService {


}