package com.amirbahadoramiri.androidlearning.views.imageloader;

import android.media.AudioManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.tools.assetsimageloader.AssetsImageLoader;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.amirbahadoramiri.androidlearning.tools.volumemanager.VolumeManager;

public class ImageLoaderActivity extends BaseActivity {

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_image_loader);
        setViewCompat();

        imageView = findViewById(R.id.imageView);
        AssetsImageLoader.load(this, "images/test.jpg", imageView, new AssetsImageLoader.CallBack() {
            @Override
            public void onError(Exception e) {
                Logger.debug(e);
            }
        });

        VolumeManager volumeManager = new VolumeManager();
        volumeManager.setVolumeWithUi(this, AudioManager.STREAM_MUSIC, 80);
        volumeManager.setVolumeWithSound(this, AudioManager.STREAM_MUSIC, 80);
        Logger.any(volumeManager.getVolume(this, AudioManager.STREAM_MUSIC));


    }
}
