package com.example.moodtracker.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.moodtracker.R;
import com.example.moodtracker.shapes.Rectangle;

public class CustomTransparentRecktangle extends View {

    private Rectangle rectangle;
    private Paint paint;

    public CustomTransparentRecktangle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setAlpha(50);
        rectangle = new Rectangle(paint,0,0,1100,2000);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rectangle.getX1(),rectangle.getY1(),rectangle.getX2(),rectangle.getY2(),paint);

    }
}
