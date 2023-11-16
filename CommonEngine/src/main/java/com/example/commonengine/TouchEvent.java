package com.example.commonengine;

public class TouchEvent {
    public static enum TouchEventType {
        TOUCH_DOWN,
        TOUCH_UP,
        CLICK,
        TOUCH_DRAG
    }

    public TouchEventType type;
    public int x;
    public int y;
}
