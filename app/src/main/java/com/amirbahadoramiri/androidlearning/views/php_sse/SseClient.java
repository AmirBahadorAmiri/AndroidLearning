package com.amirbahadoramiri.androidlearning.views.php_sse;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;

public class SseClient {

    private static final int BASE_DELAY_MS = 3000;
    private static final int MAX_DELAY_MS = 30000;

    private final OkHttpClient client = new OkHttpClient();
    private final Request request;

    private Call currentCall;
    private boolean stopped = false;
    private int retryCount = 0;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final SseListener sseListener;

    public SseClient(String url, SseListener sseListener) {
        this.request = new Request.Builder().url(url).build();
        this.sseListener = sseListener;
    }

    public void start() {
        stopped = false;
        startSse();
    }

    public void stop() {
        stopped = true;
        if (currentCall != null) {
            currentCall.cancel();
        }
        handler.removeCallbacksAndMessages(null);
        sseListener.onClosed();
    }

    private void startSse() {
        if (stopped) return;

        sseListener.onStart();

        currentCall = client.newCall(request);
        currentCall.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                if (stopped) return;
                sseListener.onError(e);
                scheduleReconnect();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                retryCount = 0; // reset backoff

                BufferedSource source = response.body().source();

                try {
                    while (!stopped && !source.exhausted()) {
                        String line = source.readUtf8Line();
                        if (line == null) break;

                        if (line.startsWith("data:")) {
                            String data = line.substring(5).trim();
                            sseListener.onEvent(data);
                        }
                    }
                } catch (Exception e) {
                    sseListener.onError(e);
                } finally {
                    if (!stopped) {
                        scheduleReconnect();
                    }
                }
            }
        });
    }

    private void scheduleReconnect() {
        int delay = Math.min(
                MAX_DELAY_MS,
                (int) Math.pow(2, retryCount++) * BASE_DELAY_MS
        );

        handler.postDelayed(this::startSse, delay);
    }

}
