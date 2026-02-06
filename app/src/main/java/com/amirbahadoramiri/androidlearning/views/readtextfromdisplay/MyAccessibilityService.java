package com.amirbahadoramiri.androidlearning.views.readtextfromdisplay;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;

import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

public class MyAccessibilityService extends AccessibilityService {

    private static final String TARGET_PACKAGE = "com.amirbahadoramiri.androidlearning";
    TextView floatingText;
    boolean isAddedText = false;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null || event.getPackageName() == null) return;

        String pkg = event.getPackageName().toString();
        if (!TARGET_PACKAGE.equals(pkg)) return;

        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) return;

        if (!isAddedText) {
            isAddedText = true;
            addText();
        }

        readNode(root);
        root.recycle();
    }

    private void readNode(AccessibilityNodeInfo node) {

        if (node == null) return;

        if (node.getPackageName() == null ||
                !TARGET_PACKAGE.contentEquals(node.getPackageName())) {
            return;
        }

        CharSequence text = node.getText();
        if (text != null && !text.toString().isEmpty()) {
            Logger.logd(text.toString());
            new Handler(Looper.getMainLooper()).post(() -> floatingText.setText(text.toString()));
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                readNode(child);
                child.recycle(); // مهم برای memory
            }
        }

    }

    public void addText() {
        floatingText = new TextView(this);
        floatingText.setText("متن شناور");
        floatingText.setTextColor(Color.WHITE);
        floatingText.setBackgroundColor(0xAA000000);
        floatingText.setPadding(24, 12, 24, 12);
        floatingText.setTextSize(14);

        WindowManager windowManager =
                (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params =
                new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                        PixelFormat.TRANSLUCENT
                );

        params.gravity = Gravity.TOP | Gravity.END;
        params.x = 24;
        params.y = 200;

        windowManager.addView(floatingText, params);
    }

    @Override
    public void onInterrupt() {
    }
}
