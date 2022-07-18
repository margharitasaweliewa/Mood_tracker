package com.example.moodtracker.ui.dashboard;

import android.graphics.Paint;

public class Circle {

    private Paint paint;
    private int offsetX;
    private int offsetY;
    private int x;
    private int y;
    private int radius;

    public Circle(int x, int y, int radius, Paint paint) {

        this.x = x;
        this.y = y;
        this.radius = radius;
        this.paint = paint;

    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getRadius() {
        return radius;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
