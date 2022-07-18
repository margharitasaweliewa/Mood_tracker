package com.example.moodtracker;



import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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

    //поля для TextView
    private TextView foodTextView;
    private TextView relaxTextView;
    private TextView sportTextView;
    private TextView sleepTextView;
    private TextView socialTextView;
    private TextView workTextView;

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
        CustomActivityChart customActivityChart = new CustomActivityChart(this,null);

    }


    /*ивент срабатывает когда мы возвращаемся в этот активити из другого
    * https://developer.android.com/guide/components/activities/activity-lifecycle
    *
    * */
    @Override
    protected void onResume() {
        super.onResume();

        //получаем интент из другого активити
        Intent intent = getIntent();

        /* убеждаемся что этот интент пришел именно с окна настроек
        колеса баланса, а не с другого
        * */
        if (intent.hasExtra("food")) {

            /* ищем компоненты, приходится делать это второй раз ибо
            *  программа очистила содержимое этих переменных
            *  после того как мы временно ушли с этого активити,
            *  т.е. после onClickWheelSettings(View view)
            *   */
            foodTextView = findViewById(R.id.textView_food);
            relaxTextView = findViewById(R.id.textView_relax);
            sportTextView = findViewById(R.id.textView_sport);
            sleepTextView = findViewById(R.id.textView_sleep);
            socialTextView = findViewById(R.id.textView_social);
            workTextView = findViewById(R.id.textView_work);

            //устанавливаем текст
            foodTextView.setText(intent.getStringExtra("food"));
            relaxTextView.setText(intent.getStringExtra("relax"));
            sportTextView.setText(intent.getStringExtra("sport"));
            sleepTextView.setText(intent.getStringExtra("sleep"));
            socialTextView.setText(intent.getStringExtra("social"));
            workTextView.setText(intent.getStringExtra("work"));

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
    //Переход на страницу настроек
    public void onClickWheelSettings(View view) {
        Intent intent = new Intent(this, BalanceWheelSettings.class);

        foodTextView = findViewById(R.id.textView_food);
        relaxTextView = findViewById(R.id.textView_relax);
        sportTextView = findViewById(R.id.textView_sport);
        sleepTextView = findViewById(R.id.textView_sleep);
        socialTextView = findViewById(R.id.textView_social);
        workTextView = findViewById(R.id.textView_work);


        String foodText = foodTextView.getText().toString();
        String relaxText = relaxTextView.getText().toString();
        String sportText = sportTextView.getText().toString();
        String sleepText = sleepTextView.getText().toString();
        String socialText = socialTextView.getText().toString();
        String workText = workTextView.getText().toString();

        intent.putExtra("food", foodText);
        intent.putExtra("relax", relaxText);
        intent.putExtra("sport", sportText);
        intent.putExtra("sleep", sleepText);
        intent.putExtra("social", socialText);
        intent.putExtra("work", workText);

        startActivity(intent);
    }
    @Override
    public void onColorSelected(int dialogId, int color) {
        //Здесь нужно написать код, когда тема меняется
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }
}