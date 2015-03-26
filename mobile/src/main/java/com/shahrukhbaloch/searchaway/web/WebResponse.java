package com.shahrukhbaloch.searchaway.web;

public class WebResponse<T> {

    private T data;

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        data = result;
    }

}
