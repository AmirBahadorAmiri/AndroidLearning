package com.amirbahadoramiri.androidlearning.tools.assetsimageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/*
 *   Created by: @AmirBahadorAmiri
 *   Created at: 2026-06-12 16:06:52
 *   Github: https://github.com/AmirBahadorAmiri
 */

public class AssetsImageLoader {

    public static void load(Context context, String imageAddress, ImageView imageView, CallBack callBack) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(context.getResources().getAssets().open(imageAddress));
            imageView.setImageBitmap(bitmap);
            callBack.onSuccess();
        } catch (Exception e) {
            callBack.onError(e);
        }
    }

    public interface CallBack {
        void onError(Exception e);
        default void onSuccess() {}
    }

}