package com.xboxgesturemapper;

import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class XboxControllerModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public XboxControllerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "XboxController";
    }

    public boolean handleKeyEvent(KeyEvent event) {
        if (event.getDevice() == null) return false;

        InputDevice device = event.getDevice();
        if (!device.getName().toLowerCase().contains("xbox")) return false;

        int keyCode = event.getKeyCode();
        String action = event.getAction() == KeyEvent.ACTION_DOWN ? "pressed" : "released";

        sendEvent("XboxButtonEvent", keyCode + ":" + action);
        return true;
    }

    private void sendEvent(String eventName, String params) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
    }

    public boolean handleMotionEvent(MotionEvent event) {
        // Optional: handle analog stick or touchpad events here
        return false;
    }
}
