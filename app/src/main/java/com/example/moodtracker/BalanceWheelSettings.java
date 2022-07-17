package com.example.moodtracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BalanceWheelSettings extends AppCompatActivity {
    private EditText balanceWheelSector;
    private TextView balanceWheelSectorchanged;
    private Button ChangeWheelSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_wheel_settings);
    }
    //Меняем наименование атрибутов колеса баланса
    public void onClickSettingsBalanceWheel(View view) {
        String newName;
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
        startActivity(intent);
    }
}