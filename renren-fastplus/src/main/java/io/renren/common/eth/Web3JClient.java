package io.renren.common.eth;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ly on 2018/1/4.
 */
public class Web3JClient {

    private Web3JClient(){}

    private volatile static Web3j web3j;

    public static Web3j getClient(){
        if(web3j==null){
            synchronized (Web3JClient.class){
                if(web3j==null){
                    web3j = Web3j.build(new HttpService(Consts.URL));
                }
            }
        }
        return web3j;
    }

    public static void main(String[] args){
        // Java 8之后：
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        //features.forEach(n -> System.out.println(n));

// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
// 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }
}
