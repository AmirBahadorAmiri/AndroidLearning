package com.amirbahadoramiri.androidlearning.views.videoview;

import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

public class VideoController implements View.OnTouchListener {
    private VideoView videoView;
    private long videoDuration;
    private long currentTime;
    private long sectionDuration;
    private float speed;

    public VideoController(VideoView videoView) {
        this.videoView = videoView;
        this.videoDuration = videoView.getDuration();
        this.sectionDuration = videoDuration / 100;
        this.currentTime = 0;
        this.speed = 0;
        videoView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                speed = event.getX() - event.getHistoricalX(0);
                if (speed < 0) {
                    moveBackward();
                } else if (speed > 0) {
                    moveForward();
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void moveBackward() {
        currentTime -= sectionDuration * speed;
        if (currentTime < 0) {
            currentTime = 0;
        }
        videoView.seekTo((int) currentTime);
    }

    private void moveForward() {
        currentTime += sectionDuration * speed;
        if (currentTime > videoDuration) {
            currentTime = videoDuration;
        }
        videoView.seekTo((int) currentTime);
    }

}