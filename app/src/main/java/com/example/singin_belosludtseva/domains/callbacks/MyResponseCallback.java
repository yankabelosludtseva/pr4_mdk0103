package com.example.singin_belosludtseva.domains.callbacks;

public interface MyResponseCallback {
    void onComplete(String result);

    void onError(String error);
}
