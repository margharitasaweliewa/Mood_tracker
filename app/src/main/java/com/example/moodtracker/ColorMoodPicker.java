package com.example.moodtracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moodtracker.databinding.ActivityColorMoodPickerBinding;
import com.example.moodtracker.databinding.ActivityMainBinding;
import com.example.moodtracker.ui.CustomTransparentRecktangle;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.jaredrummler.android.colorpicker.ColorShape;

public class ColorMoodPicker extends AppCompatActivity implements ColorPickerDialogListener {
public Slider moodSlider;
public String currentMood;
public TextView moodChosen;
public Button mainColorPicker;
public Button saveColorMood;
public int colorChosen;
private ActivityColorMoodPickerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.TranslucentBackground);
        super.onCreate(savedInstanceState);


        binding = ActivityColorMoodPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_color_mood_picker);
        this.setFinishOnTouchOutside(false);



        //получаем Intent от MainActivity
        Intent intent = getIntent();
        moodSlider = findViewById(R.id.moodSlider);
        mainColorPicker = findViewById(R.id.mainColorPicker);
        saveColorMood = findViewById(R.id.saveColorMood);
        //Тут надо бы брать из МейнАктивити цвет тоже, наверное
        colorChosen = ContextCompat.getColor(ColorMoodPicker.this,R.color.purple_700);
        //Обьявляем интент из этого активити в MainActivity
        Intent sendingIntent = new Intent(getApplicationContext(), MainActivity.class);
        //устанавливаем флаг что-бы стэк активити почистился и нам опять вернуло MainActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        /* у меня был баг с обьявлением ивента onClick в .xml файле в теге кнопки, поєтому лучше прописывать ивенты в коде
         * вот так */
        saveColorMood.setOnClickListener(v -> {

            // при нажатии кнопки забираем цвет
            sendingIntent.putExtra("color",colorChosen);
            //отправляем интент обратно в mainActivity
            startActivity(sendingIntent);

        });


        //adding listener to slider
        moodSlider.addOnChangeListener((slider, value, fromUser) -> {
            //В зависимости от текущего значения ставлю значение настроения
            switch ((int)value){
                case 0:
                    currentMood = "Severe depressive";
                    break;
                case 1:
                    currentMood = "Depressive";
                    break;
                case 2:
                    currentMood = "Sad";
                    break;
                case 3:
                    currentMood = "Indifferent";
                    break;
                case 4:
                    currentMood = "Happy";
                    break;
                case 5:
                    currentMood = "Hypomanic";
                    break;
                case 6:
                    currentMood = "Manic";
                    break;
            }
            //Ставим значение текстового поля в завимости от выбранного настроения
            moodChosen = findViewById(R.id.moodChosen);
            moodChosen.setText(currentMood);

        });



    }
    //Описываем поведение при нажатии кнопки
    public void onClickColorButton(View view) {
        switch (view.getId()) {
            case R.id.mainColorPicker:
                createColorPickerDialog();
                break;
        }
    }
    //Функция для перехода на экран настроек
    private void createColorPickerDialog() {
        ColorPickerDialog.newBuilder()
                .setColor(Color.RED)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setAllowCustom(true)
                .setAllowPresets(true)
                .setColorShape(ColorShape.SQUARE)
                .setDialogId(1)
                .show(this);
    }
    @Override
    public void onColorSelected(int dialogId, int color) {
        //Новый цвет присваиваю кнопке
        Drawable buttonDrawable = mainColorPicker.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(buttonDrawable, color);
        mainColorPicker.setBackground(buttonDrawable);
        colorChosen = color;
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }
}