package com.amirbahadoramiri.androidlearning.views.readtextfromdisplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

public class FloatingTextManager {

    private final Context context;
    private final WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private TextView floatingText;

    private int initialX;
    private int initialY;
    private float initialTouchX;
    private float initialTouchY;

    public FloatingTextManager(Context context) {
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void show(String text) {
        if (floatingText != null) return;

        floatingText = new TextView(context);
        floatingText.setText(text);
        floatingText.setTextColor(Color.WHITE);
        floatingText.setBackgroundColor(0xAA000000);
        floatingText.setPadding(32, 16, 32, 16);
        floatingText.setTextSize(14);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 100;
        params.y = 300;

        floatingText.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    initialX = params.x;
                    initialY = params.y;
                    initialTouchX = event.getRawX();
                    initialTouchY = event.getRawY();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    params.x = initialX + (int) (event.getRawX() - initialTouchX);
                    params.y = initialY + (int) (event.getRawY() - initialTouchY);
                    windowManager.updateViewLayout(floatingText, params);
                    return true;
            }
            return false;
        });

        windowManager.addView(floatingText, params);
    }

    public void updateText(String text) {
        if (floatingText == null) return;
        new Handler(Looper.getMainLooper()).post(() ->
                floatingText.setText(text)
        );
    }

    public void remove() {
        if (floatingText != null) {
            windowManager.removeView(floatingText);
            floatingText = null;
        }
    }
}
