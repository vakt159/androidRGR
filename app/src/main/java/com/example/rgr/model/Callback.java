package com.example.rgr.model;

public interface Callback<T> {

    void onError(Throwable error);

    void onResults(T data);
}
