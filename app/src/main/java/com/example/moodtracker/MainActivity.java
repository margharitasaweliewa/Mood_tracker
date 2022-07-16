package com.example.moodtracker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moodtracker.ui.dashboard.CustomActivityChart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.moodtracker.databinding.ActivityMainBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.jaredrummler.android.colorpicker.ColorShape;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {
    //Кнопка для выбора цвета
    private Button colorPicker;
    private ActivityMainBinding binding;
    private static final int firstId = 1;
    private Button createDailyPicture;
// id нужны для реализации метода onColorSelected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        //Инициализируем поля
        colorPicker = findViewById(R.id.colorPicker);
        createDailyPicture = findViewById(R.id.dailypicture);
        //Описываем поведение при нажатии на кнопку


        //Весим обработчик события при нажатии на кнопку


        CustomActivityChart customActivityChart = new CustomActivityChart(this,null);

    }
    private void createColorPickerDialog() {
        ColorPickerDialog.newBuilder()
                .setColor(Color.RED)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setAllowCustom(true)
                .setAllowPresets(true)
                .setColorShape(ColorShape.SQUARE)
                .setDialogId(1)
                .show(this);
// полный список атрибутов класса ColorPickerDialog смотрите ниже
    }
    public void onClickColorButton(View view) {
        switch (view.getId()) {
            case R.id.colorPicker:
                createColorPickerDialog();
                break;
        }
    }
    public void onClickDailyPictureButton(View view) {
        createDailyPicture = findViewById(R.id.dailypicture);
        //Описываем поведение при нажатии на кнопку
        createDailyPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setTitle("Daily Picture");
                builder.setMessage("Do you want to choose a picture or take a photo?");
                builder.setNeutralButton("CHOOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("TAKE PHOTO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public void onColorSelected(int dialogId, int color) {
        //Здесь нужно написать код, когда тема меняется
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }
}