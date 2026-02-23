package com.amirbahadoramiri.androidlearning.views.readtextfromdisplay;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

@SuppressLint("AccessibilityPolicy")
public class MyAccessibilityService extends AccessibilityService {

    private static final String TARGET_PACKAGE = "com.amirbahadoramiri.androidlearning";
    FloatingTextManager floating;
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
            new Handler(Looper.getMainLooper()).post(() -> floating.updateText(text.toString()));
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
        floating = new FloatingTextManager(this);
        floating.show("سلام! 👋");
    }

    @Override
    public void onInterrupt() {
    }
}
