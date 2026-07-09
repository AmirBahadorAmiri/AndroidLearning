package com.amirbahadoramiri.androidlearning.views.flashlight;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SwitchCompat;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.tools.cameraflasher.Flasher;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

public class FlashActivity extends BaseActivity {

    Flasher flasher = new Flasher();
    AppCompatImageView lightImageview;
    SwitchCompat switchButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_flashlight);
        setViewCompat();
        findViews();
        setupViews();
    }

    public void fadeIn(int duration) {
//        AnimationSet set = new AnimationSet(true);
        Animation alphaAnimation = new AlphaAnimation(0.2F, 1F);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);
//        set.addAnimation(alphaAnimation);
        lightImageview.startAnimation(alphaAnimation);
    }

    public void fadeOut(int duration) {
//        AnimationSet set = new AnimationSet(true);
        Animation alphaAnimation = new AlphaAnimation(1F, 0.2F);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);
//        set.addAnimation(alphaAnimation);
        lightImageview.startAnimation(alphaAnimation);
    }

    private void findViews() {
        lightImageview = findViewById(R.id.light_imageview);
        switchButton = findViewById(R.id.switchButton);
    }

    private void setupViews() {
        fadeOut(0);
        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            flasher.init(FlashActivity.this);
            if (isChecked) {
                flasher.turnOn(new Flasher.CallBack() {
                    @Override
                    public void onError(Throwable throwable) {
                        Logger.debug(throwable);
                    }
                    @Override
                    public void onSuccess() {
                        fadeIn(1000);
                        Logger.debug("turnOn");
                    }
                });
            } else {
                flasher.turnOff(new Flasher.CallBack() {
                    @Override
                    public void onError(Throwable throwable) {
                        Logger.debug(throwable);
                    }

                    @Override
                    public void onSuccess() {
                        fadeOut(1000);
                        Logger.debug("turnOff");
                    }
                });
            }
        });
    }
}
