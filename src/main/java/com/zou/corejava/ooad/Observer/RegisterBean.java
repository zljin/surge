package com.zou.corejava.ooad.Observer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterBean {

    @Bean(name = "publisheryyds")
    public Publisher publisheryyds(){
        return new Publisher("");
    }

    @Bean
    public Listener listener1(){
        return new Listener("脑残粉1");
    }

    @Bean
    public Listener listener2(){
        return new Listener("脑残粉2");
    }

    @Bean
    public Listener listener3(){
        return new Listener("脑残粉3");
    }


}
