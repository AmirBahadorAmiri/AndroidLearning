package com.amirbahadoramiri.androidlearning.views.videoview;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class VideoShowerActivity extends BaseActivity {

    CustomVideoView video;
    AppCompatImageView video_seekback, video_play, video_seekforward;
    AppCompatTextView video_position,video_duration;
    AppCompatSeekBar video_seekbar;
    ConstraintLayout controller_view;
    int saved_time = 0;
    public final int SEEK_TIME = 15000;

    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_video_shower);
        setViewCompat();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_VIDEO}, 0);

        video = findViewById(R.id.videoView);
        video_seekback = findViewById(R.id.video_seekback);
        video_play = findViewById(R.id.video_play);
        video_seekforward = findViewById(R.id.video_seekforward);
        controller_view = findViewById(R.id.controller_view);
        video_seekbar = findViewById(R.id.video_seekbar);
        video_position = findViewById(R.id.video_position);
        video_duration = findViewById(R.id.video_duration);

        String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/0.mp4";
        video.setVideoPath(dir);
        video.setOnPreparedListener(mp -> {
            video_seekbar.setMax(video.getDuration());
            convert(video.getDuration(), new TimeConvertor() {
                @Override
                public void timer(String h, String m, String s) {
                    String time = h+":"+m+":"+s;
                    video_duration.setText(time);
                }
            });
            video_play.setImageDrawable(ContextCompat.getDrawable(VideoShowerActivity.this, R.drawable.video_pause));
            if (savedInstanceState != null) {
                Logger.any(savedInstanceState.getInt("saved_time", 0));
                video.seekTo(savedInstanceState.getInt("saved_time", 0));
            }
            video.start();
        });

        video.setOnClickListener(v -> {
            if (controller_view.getVisibility() == View.VISIBLE) {
                controller_view.setVisibility(View.GONE);
            } else {
                controller_view.setVisibility(View.VISIBLE);
            }
        });

        video.setOnCompletionListener(mp -> {
            video_play.setImageDrawable(ContextCompat.getDrawable(VideoShowerActivity.this, R.drawable.video_play));
        });

        video_play.setOnClickListener(v -> {
            if (video.isPlaying()) {
                video.pause();
                video_play.setImageDrawable(ContextCompat.getDrawable(VideoShowerActivity.this, R.drawable.video_play));
            } else {
                video.start();
                video_play.setImageDrawable(ContextCompat.getDrawable(VideoShowerActivity.this, R.drawable.video_pause));
            }
        });

        video_seekback.setOnClickListener(v -> video.seekTo(Math.max(0, (video.getCurrentPosition() - SEEK_TIME))));

        video_seekforward.setOnClickListener(v -> video.seekTo(Math.min(video.getDuration(), (video.getCurrentPosition() + SEEK_TIME))));

        video_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if ( fromUser )
                    video.seekTo(progress);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }
                    @Override
                    public void onNext(Long aLong) {
                        video_seekbar.setProgress((video.getCurrentPosition()));

                        convert(video.getCurrentPosition(), new TimeConvertor() {
                            @Override
                            public void timer(String h, String m, String s) {
                                String time = h+":"+m+":"+s;
                                video_position.setText(time);
                            }
                        });
                    }
                    @Override public void onError(@NonNull Throwable e) {}
                    @Override public void onComplete() {}
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (video != null) {
            video.stopPlayback();
        }
        disposable.dispose();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (video != null) {
            saved_time = video.getCurrentPosition();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("saved_time", saved_time);
    }

    public void convert(int milies,TimeConvertor convertor) {
        int h = milies / 3600000;
        int m1 = milies % 3600000;
        int m = m1 / 60000;
        int s1 = m1 % 60000;
        int s = s1 / 1000;

        String hour = String.valueOf(h);
        String minute = String.valueOf(m);
        String second = String.valueOf(s);

        if ( h<10 ) hour = 0 + hour;
        if ( m<10 ) minute = 0 + minute;
        if ( s<10 ) second = 0 + second;

        convertor.hour(h);
        convertor.minute(m);
        convertor.second(s);

        convertor.hour(hour);
        convertor.minute(minute);
        convertor.second(second);

        convertor.timer(h,m,s);
        convertor.timer(hour,minute,second);

    }

}