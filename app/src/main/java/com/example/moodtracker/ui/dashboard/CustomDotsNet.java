package com.example.moodtracker.ui.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.moodtracker.R;
import com.example.moodtracker.shapes.Circle;

import java.util.ArrayList;
import java.util.List;

public class CustomDotsNet extends View {

   List<Circle> dots;

   public static final int DOTS_NUMBER = 6;

   private Paint linePaint;

    public CustomDotsNet(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);

        this.dots = new ArrayList<>();

        int radius = 25;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.purple_700));
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(getResources().getColor(R.color.purple_700));
        linePaint.setStrokeWidth(7);

        int baseX = 385;
        int baseY = 340;

        dots.add(new Circle(baseX-45, baseY-70,radius,paint));
        dots.add(new Circle(baseX+45, baseY-70,radius,paint));
        dots.add(new Circle(baseX+80, baseY,radius,paint));
        dots.add(new Circle(baseX+45, baseY+70,radius,paint));
        dots.add(new Circle(baseX-45, baseY+70,radius,paint));
        dots.add(new Circle(baseX-80, baseY,radius,paint));

    }

    float dX, dY;
    Circle c;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                this.c = null;
                for (Circle c:
                     dots) {
                    if((event.getX() >= c.getX() - c.getRadius()
                            && event.getX() <= c.getX() + c.getRadius())
                    && (event.getY() >= c.getY() - c.getRadius()
                            && event.getY() <= c.getY() + c.getRadius())) {
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

            canvas.drawLine(dot.getX(),
                            dot.getY(),
                            dots.get((DOTS_NUMBER - i == 1 ? 0 : i+1)).getX(),
                            dots.get((DOTS_NUMBER - i == 1 ? 0 : i+1)).getY(),
                            linePaint);
        }


    }
}
