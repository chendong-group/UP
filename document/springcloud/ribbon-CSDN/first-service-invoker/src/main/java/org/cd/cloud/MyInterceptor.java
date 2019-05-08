package org.cd.cloud;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @classname: MyInterceptor
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-17 13:58
 */
public class MyInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("========这是自定义拦截器========");
        System.out.println("原来的URI: " + httpRequest.getURI());
        //更换URI
        MyHttpRequest myHttpRequest = new MyHttpRequest(httpRequest);
        System.out.println("更换后的URI: " + myHttpRequest.getURI());
        return clientHttpRequestExecution.execute(myHttpRequest, bytes);
    }
}
