package com.amirbahadoramiri.androidlearning.views.php_sse;

public interface SseListener {

    default void onStart() {}

    void onEvent(String data);

    void onError(Throwable t);

    default void onClosed() {}
}
