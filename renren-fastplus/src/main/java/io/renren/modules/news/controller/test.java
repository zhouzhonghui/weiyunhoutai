package io.renren.modules.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.renren.modules.news.domain.NewsInfoReq;

import java.io.IOException;

public class test {
    public static void main(String[] args) {
        String str = "{\"covertype\":0,\"newstype\":0,\"attaid\":0,\"istop\":0,\"ishot\":0,\"newsfrom\":0,\"publishtime\":\"\",\"status\":1,\"content\":\"<p>温热温热<img src='https://shinechain.oss-cn-beijing.aliyuncs.com/data/20180408/be4a795c7d994285bda57dca17ec497c.png'/></p>\"}" ;


        System.out.println(str.replaceAll("'","\""));
        ObjectMapper objectMapper = new ObjectMapper() ;
        NewsInfoReq req = null ;
        try {
            req = objectMapper.readValue(str, NewsInfoReq.class) ;
            System.out.println(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
