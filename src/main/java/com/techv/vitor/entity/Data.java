package com.techv.vitor.entity;

import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class Data<T> {

    private T data;
    private Map<String, String> headers;

    public Data() {

    }

    public Map<String, String> convertToMap(HttpHeaders headers) {
        Map<String, String> headerMap = new HashMap<>();
        headers.forEach((key, value) -> headerMap.put(key, String.join(",", value)));
        return headerMap;
    }

    public Data(T data, Map<String, String> headers) {
        this.headers = headers;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
