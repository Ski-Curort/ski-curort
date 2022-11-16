package com.example.skicurort.weather;

import org.springframework.stereotype.Component;

@Component
public class HttpClientWrapperProvider {
    private static HttpClientWrapper httpClientWrapper;

    public static HttpClientWrapper getInstance() {
        if (httpClientWrapper == null) {
            httpClientWrapper = new HttpClientWrapper();
        }
        return httpClientWrapper;
    }

}
