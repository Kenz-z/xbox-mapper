package com.xboxgesturemapper;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class AccessibilityBridgeModule extends ReactContextBaseJavaModule {

    public AccessibilityBridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "AccessibilityBridge";
    }

    @ReactMethod
    public void openAccessibilitySettings() {
        Context context = getReactApplicationContext();
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
