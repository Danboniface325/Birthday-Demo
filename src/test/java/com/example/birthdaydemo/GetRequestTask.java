package com.example.birthdaydemo;

import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class GetRequestTask<T> implements Runnable {

    private final URI url;
    private final HttpEntity<T> request;
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    public GetRequestTask(URI url, HttpEntity<T> request) {
        this.url = url;
        this.request = request;
    }

    @Override
    public void run() {
        this.restTemplate.getForEntity(url, String.class);
    }
}
