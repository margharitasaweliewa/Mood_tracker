package com.example.moodtracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moodtracker.ui.today.TodayFragment;

import java.util.ArrayList;
import java.util.List;

public class BalanceWheelSettings extends AppCompatActivity {


    private Button saveWheelSettings;

    //обьекты для текстовых полей
    private EditText foodEditText;
    private EditText relaxEditText;
    private EditText sportEditText;
    private EditText sleepEditText;
    private EditText socialEditText;
    private EditText workEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_wheel_settings);


        //получаем Intent от MainActivity
        Intent intent = getIntent();

        //получаем названия с MainActivity
        String food_text = intent.getStringExtra("food");
        String relax_text = intent.getStringExtra("relax");
        String sport_text = intent.getStringExtra("sport");
        String sleep_text = intent.getStringExtra("sleep");
        String social_text = intent.getStringExtra("social");
        String work_text = intent.getStringExtra("work");

        //находим элементы в настройках
        foodEditText = findViewById(R.id.editText_food);
        relaxEditText = findViewById(R.id.editText_relax);
        sportEditText = findViewById(R.id.editText_sport);
        sleepEditText = findViewById(R.id.editText_sleep);
        socialEditText = findViewById(R.id.editText_social);
        workEditText = findViewById(R.id.editText_work);

        //устанавливаем туда текст
        foodEditText.setText(food_text);
        relaxEditText.setText(relax_text);
        sportEditText.setText(sport_text);
        sleepEditText.setText(sleep_text);
        socialEditText.setText(social_text);
        workEditText.setText(work_text);

        saveWheelSettings = findViewById(R.id.changeWheelSettings);

        //Обьявляем интент из этого активити в MainActivity
        Intent sendingIntent = new Intent(getApplicationContext(), MainActivity.class);
        //устанавливаем флаг что-бы стэк активити почистился и нам опять вернуло MainActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        /* у меня был баг с обьявлением ивента onClick в .xml файле в теге кнопки, поєтому лучше прописывать ивенты в коде
        * вот так */
        saveWheelSettings.setOnClickListener(v -> {

            // при нажатии кнопки собираем текст с текстовых полей
            sendingIntent.putExtra("food",foodEditText.getText().toString());
            sendingIntent.putExtra("relax", relaxEditText.getText().toString());
            sendingIntent.putExtra("sport", sportEditText.getText().toString());
            sendingIntent.putExtra("sleep", sleepEditText.getText().toString());
            sendingIntent.putExtra("social", socialEditText.getText().toString());
            sendingIntent.putExtra("work",workEditText.getText().toString());

            //отправляем интент обратно в mainActivity
            startActivity(sendingIntent);

        });
    }

    //Меняем наименование атрибутов колеса баланса
    public void onClickSaveSettingsBalanceWheel(View view) {



      /*  String newName;
        //Я тут просто их перебираю, возможно, это не самая лучшая идея(но хардкодить можно в целом)
        //Food
        balanceWheelSector = findViewById(R.id.editText_food);
        if(balanceWheelSector.getText().toString().trim().equals("")){}
        else {//Меняем наименование в настройках
            newName = balanceWheelSector.getText().toString().trim();
            balanceWheelSector.setText(newName);
            //Меняем наименование на основном экране
            balanceWheelSectorchanged = findViewById(R.id.textView_food);
            balanceWheelSectorchanged.setText(newName);
        }
        //Relax
        balanceWheelSector = findViewById(R.id.editText_relax);
        if(balanceWheelSector.getText().toString().trim().equals("")){}
        else {//Меняем наименование в настройках
            newName = balanceWheelSector.getText().toString().trim();
            balanceWheelSector.setText(newName);
            //Меняем наименование на основном экране
            balanceWheelSectorchanged = findViewById(R.id.textView_relax);
            balanceWheelSectorchanged.setText(newName);
        }
        //Sport
        balanceWheelSector = findViewById(R.id.editText_sport);
        if(balanceWheelSector.getText().toString().trim().equals("")){}
        else {//Меняем наименование в настройках
            newName = balanceWheelSector.getText().toString().trim();
            balanceWheelSector.setText(newName);
            //Меняем наименование на основном экране
            balanceWheelSectorchanged = findViewById(R.id.textView_sport);
            balanceWheelSectorchanged.setText(newName);
        }
        //Social
        balanceWheelSector = findViewById(R.id.editText_social);
        if(balanceWheelSector.getText().toString().trim().equals("")){}
        else {//Меняем наименование в настройках
            newName = balanceWheelSector.getText().toString().trim();
            balanceWheelSector.setText(newName);
            //Меняем наименование на основном экране
            balanceWheelSectorchanged = findViewById(R.id.textView_social);
            balanceWheelSectorchanged.setText(newName);
        }
        //Sleep
        balanceWheelSector = findViewById(R.id.editText_sleep);
        if(balanceWheelSector.getText().toString().trim().equals("")){}
        else {//Меняем наименование в настройках
            newName = balanceWheelSector.getText().toString().trim();
            balanceWheelSector.setText(newName);
            //Меняем наименование на основном экране
            balanceWheelSectorchanged = findViewById(R.id.textView_sleep);
            balanceWheelSectorchanged.setText(newName);
        }
        //Переходим обратно на главный экран
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); */


    }
}