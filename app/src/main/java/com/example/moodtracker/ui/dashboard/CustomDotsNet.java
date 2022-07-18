package com.example.moodtracker.ui.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomDotsNet extends View {

   List<Circle> dots;

   public static final int DOTS_NUMBER = 6;

    public CustomDotsNet(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);

        this.dots = new ArrayList<>();

        int radius = 25;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        int baseX = 550;
        int baseY = 420;

        dots.add(new Circle(baseX-25, baseY-25,radius,paint));
        dots.add(new Circle(baseX+25, baseY-25,radius,paint));
        dots.add(new Circle(baseX+35, baseY,radius,paint));
        dots.add(new Circle(baseX+25, baseY+25,radius,paint));
        dots.add(new Circle(baseX-25, baseY+25,radius,paint));
        dots.add(new Circle(baseX-35, baseY,radius,paint));

    }

    float dX, dY;
    Circle c;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                for (Circle c:
                     dots) {
                    if((event.getX() >= c.getX() - c.getRadius()
                            && event.getX() <= c.getX() + c.getRadius())
                    && (event.getY() >= c.getY() - c.getRadius()
                            && event.getY() <= c.getY() + c.getRadius())) {
                      //  if(event.getRawX() > c.getX() && event.getRawY() > c.getY()){
                        dX = c.getX() - event.getRawX();
                        dY = c.getY() - event.getRawY();
                        this.c = c;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:

                if(c != null){

                c.setX((int)(event.getRawX() + dX));
                c.setY((int)(event.getRawY() + dY));

                }
                invalidate();

                break;
            default:
                return false;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < DOTS_NUMBER; i++) {
            Circle dot = dots.get(i);
            canvas.drawCircle(dot.getX(),
                    dot.getY()
                    , dot.getRadius(),
                    dot.getPaint());

        }


    }
}
