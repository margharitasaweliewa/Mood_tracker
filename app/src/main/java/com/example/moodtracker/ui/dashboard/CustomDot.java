package com.example.moodtracker.ui.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomDot extends View {

    private Paint paintCirlce;

    public CustomDot(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);

        paintCirlce = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    float dX,dY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                dX = this.getX() - event.getRawX();
                dY = this.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:

                this.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawCircle((int)(this.getWidth() / 2),
                (int)(this.getHeight() / 2), 25, paintCirlce);

    }
}
