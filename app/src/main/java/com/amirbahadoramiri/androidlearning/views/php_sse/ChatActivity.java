package com.amirbahadoramiri.androidlearning.views.php_sse;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityChatBinding;

public class ChatActivity extends BaseActivity {

    ActivityChatBinding binding;
    private static final String BASE_URL = "http://10.218.213.169/web/testing/sse/sse.php";
    StringBuilder stringBuilder = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setViewCompat();

        binding.textview.setMovementMethod(new ScrollingMovementMethod());

//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(BASE_URL)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Logger.logd("error: " + e.getMessage());
//                call.
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                BufferedSource source = response.body().source();
//
//                while (!source.exhausted()) {
//                    String line = source.readUtf8Line();
//                    if (line != null && line.startsWith("data:")) {
//                        String data = line.replace("data:", "").trim();
//                        sb.append(data+"\n");
//                        binding.edittext.setText(sb.toString());
//                    }
//                }
//            }
//        });


        SseClient sseClient = new SseClient(BASE_URL, new SseListener() {
            @Override
            public void onEvent(String data) {
                runOnUiThread(() -> {
                    stringBuilder.append(data).append("\n");
                    if (binding.textview.getLineCount() > 500) {
                        stringBuilder.delete(0, 500);
                    }
                    binding.textview.setText(stringBuilder.toString());
                    binding.textview.post(() -> {
                        int scrollAmount = binding.textview.getLayout().getLineTop(binding.textview.getLineCount()) - binding.textview.getHeight();
                        binding.textview.scrollTo(0, Math.max(scrollAmount, 0));
                    });
                });
            }

            @Override
            public void onError(Throwable t) {
                runOnUiThread(() -> {
                    stringBuilder.append(t.getMessage()).append("\n");
                    if (binding.textview.getLineCount() > 500) {
                        stringBuilder.delete(0, 500);
                    }
                    binding.textview.setText(stringBuilder.toString());
                    binding.textview.post(() -> {
                        int scrollAmount = binding.textview.getLayout().getLineTop(binding.textview.getLineCount()) - binding.textview.getHeight();
                        binding.textview.scrollTo(0, Math.max(scrollAmount, 0));
                    });
                });
            }

        });
        sseClient.start();

    }
}
