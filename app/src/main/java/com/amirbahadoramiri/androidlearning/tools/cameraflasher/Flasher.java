package com.amirbahadoramiri.androidlearning.tools.cameraflasher;

import android.content.Context;
import android.hardware.camera2.CameraManager;

/*
 *   Created by: @AmirBahadorAmiri
 *   Created at: 2026-06-12 15:12:34
 *   Github: https://github.com/AmirBahadorAmiri
 */

public class Flasher {
    private CameraManager cameraManager;

    public CameraManager createCameraManager(Context context) {
        return (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    public void init(Context context) {
        if (cameraManager == null) {
            cameraManager = createCameraManager(context);
        }
    }

    public void turnOn(CallBack callBack) {
        try {
            cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], true);
            callBack.onSuccess();
        } catch (Exception e) {
            callBack.onError(e);
        }
    }

    public void turnOff(CallBack callBack) {
        try {
            cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], false);
            callBack.onSuccess();
        } catch (Exception e) {
            callBack.onError(e);
        }
    }

    public interface CallBack {
        default void onSuccess() {}
        void onError(Throwable throwable);
    }

}