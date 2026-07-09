package com.amirbahadoramiri.androidlearning.tools.volumemanager;

import android.content.Context;
import android.media.AudioManager;

/*
 *   Created by: @AmirBahadorAmiri
 *   Created at: 2026-06-12 16:55:37S
 *   Github: https://github.com/AmirBahadorAmiri
 */

public class VolumeManager {

    private AudioManager audioManager;

    /**
     * context , AudioManager.STREAM_MUSIC <br>
     * context , AudioManager.STREAM_ALARM <br>
     * context , AudioManager.STREAM_VOICE_CALL <br>
     * context , AudioManager.STREAM_RING <br>
     * **/

    public int getVolume(Context context, int flag) {
        return getManager(context).getStreamVolume(flag);
    }

    public void setVolumeWithUi(Context context, int flag, int percent) {
        int max = getManager(context).getStreamMaxVolume(flag);
        getManager(context).setStreamVolume(flag, percent * max / 100, AudioManager.FLAG_SHOW_UI);
    }

    public void setVolumeWithSound(Context context, int flag, int percent) {
        int max = getManager(context).getStreamMaxVolume(flag);
        getManager(context).setStreamVolume(flag, percent * max / 100, AudioManager.FLAG_PLAY_SOUND);
    }

    public AudioManager createManager(Context context) {
        return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public AudioManager getManager(Context context) {
        if (audioManager == null) audioManager = createManager(context);
        return audioManager;
    }

}