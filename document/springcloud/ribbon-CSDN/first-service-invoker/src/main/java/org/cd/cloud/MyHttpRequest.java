package org.cd.cloud;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @classname: MyHttpRequest
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-17 14:30
 */
public class MyHttpRequest implements HttpRequest {

    private HttpRequest sourceRequest;

    public MyHttpRequest() {}

    public MyHttpRequest(HttpRequest request) {
        this.sourceRequest = request;
    }

    @Override
    public HttpMethod getMethod() {
        return sourceRequest.getMethod();
    }

    @Override
    public URI getURI() {
        try {
            String oldUri = sourceRequest.getURI().toString();
            System.out.println("Old URI: " + oldUri);
            URI newUri = new URI("http://localhost:8080/message");
            return newUri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return sourceRequest.getURI();
    }

    @Override
    public HttpHeaders getHeaders() {
        return sourceRequest.getHeaders();
    }
}
