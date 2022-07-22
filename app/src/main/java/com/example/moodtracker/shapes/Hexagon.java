package com.example.moodtracker.shapes;



import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;



public class Hexagon extends Drawable {

    private Path point = new Path();
    public static final int SIDES = 6;

    private Path hexagon = new Path();


    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Hexagon(int color) {

        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);
        hexagon.setFillType(Path.FillType.EVEN_ODD);
    }



    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }


    @Override
    public int getOpacity() {
        return paint.getAlpha();
    }


    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }



    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        computeHex(bounds);
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawPath(hexagon, paint);
    }

    public void computeHex(Rect bounds) {

        final int width = bounds.width();
        final int height = bounds.height();
        final int size = Math.min(width, height);
        final int centerX = bounds.left + (width / 2);
        final int centerY = bounds.top + (height / 2);

        hexagon.reset();

        hexagon.addPath(makeHex(size, centerX, centerY));


        hexagon.addPath(makeHex((int) (size * .8f), centerX, centerY));
    }

    private Path makeHex(int size, int centerX, int centerY) {
        final float section = (float) (2.0 * Math.PI / SIDES);
        int radius = size / 2;
        Path hexagon = point;
        hexagon.reset();
        hexagon.moveTo(
                (float) (centerX + radius * Math.cos(0)),
                (float) (centerY + radius * Math.sin(0)));



        for (int i = 1; i < SIDES; i++) {
            hexagon.lineTo(
                    (float) (centerX + radius * Math.cos(section * i)),
                    (float) (centerY + radius * Math.sin(section * i)));
        }



        hexagon.close();
        return hexagon;
    }
}
