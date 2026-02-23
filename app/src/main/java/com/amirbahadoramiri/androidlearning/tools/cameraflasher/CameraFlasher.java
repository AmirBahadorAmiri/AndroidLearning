package com.amirbahadoramiri.androidlearning.tools.cameraflasher;

import android.content.Context;
import android.hardware.camera2.CameraManager;

import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

public class CameraFlasher {
    CameraManager cameraManager;

    public void init(Context context) {
        if (cameraManager == null) {
            cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        }
    }

    public void turnOnFlash(OnFlashLightListener onFlashLightListener) {
        try {
            cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], true);
            onFlashLightListener.onSuccess(true);
        } catch (Exception e) {
            onFlashLightListener.onSuccess(false);
            Logger.logd(e.getMessage());
        }
    }

    public void turnOffFlash(OnFlashLightListener onFlashLightListener) {
        try {
            cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], false);
            onFlashLightListener.onSuccess(true);
        } catch (Exception e) {
            onFlashLightListener.onSuccess(false);
            Logger.logd(e.getMessage());
        }
    }


}