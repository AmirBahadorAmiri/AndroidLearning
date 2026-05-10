package com.amirbahadoramiri.androidlearning.views.eventbus.withBus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class EventBusActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_eventbus);
        setViewCompat();

        Button button3 = findViewById(R.id.button3);

        button3.setOnClickListener(v -> {
            RxBus.getSubject().onNext(RxBus.user());
            startActivity(new Intent(this, EventBusReceiverActivity.class));
        });
    }
}
