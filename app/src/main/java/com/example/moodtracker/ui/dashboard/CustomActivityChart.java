package com.example.moodtracker.ui.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.moodtracker.shapes.Hexagon;

import java.util.ArrayList;
import java.util.List;

public class CustomActivityChart extends View {

    private List<Hexagon> hexagons;

    //making smaller hexagons with this step
    private static final int STEP = 120;
    private static final int HEXAGONS = 3;


    public CustomActivityChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    //draw initial components
    private void init() {

        List<Integer> coords = new ArrayList<>();
        coords.add(200);
        coords.add(50);
        coords.add(900);
        coords.add(750);

         hexagons = new ArrayList<>();



        //3 hexes
        for (int i = 0; i < HEXAGONS; i++) {
            hexagons.add(new Hexagon(Color.GRAY));
            hexagons.get(i).
                    computeHex(new Rect(coords.get(0)
                                        ,coords.get(1),
                                        coords.get(2),
                                        coords.get(3)));

            for (int j = 0; j < coords.size(); j++) {
                if (j < 2)
                    coords.set(j,coords.get(j) + STEP);
                else
                    coords.set(j,coords.get(j) - STEP);
            }
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Hexagon hex:
             hexagons) {
            hex.draw(canvas);
        }
    }
}
