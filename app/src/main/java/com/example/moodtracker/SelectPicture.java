package com.example.moodtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.moodtracker.databinding.ActivitySelectPictureBinding;

public class SelectPicture extends AppCompatActivity {

    private ActivitySelectPictureBinding binding;
    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private ImageButton button5;
    private ImageButton button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);

        binding = ActivitySelectPictureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_color_mood_picker);
        this.setFinishOnTouchOutside(false);

        //получаем Intent от MainActivity
        Intent intent = getIntent();
        button1 = findViewById(R.id.imageButton_tree1);
        button2 = findViewById(R.id.imageButton_tree2);
        button3 = findViewById(R.id.imageButton_tree3);
        button4 = findViewById(R.id.imageButton_tree4);
        button5 = findViewById(R.id.imageButton_tree5);
        button6 = findViewById(R.id.imageButton_tree6);

        //Обьявляем интент из этого активити в MainActivity
        Intent sendingIntent = new Intent(getApplicationContext(), MainActivity.class);
        //устанавливаем флаг что-бы стэк активити почистился и нам опять вернуло MainActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        /* у меня был баг с обьявлением ивента onClick в .xml файле в теге кнопки, поєтому лучше прописывать ивенты в коде
         * вот так */
        button1.setOnClickListener(v -> {

            // при нажатии кнопки забираем цвет
            sendingIntent.putExtra("picture", "tree1");
            //отправляем интент обратно в mainActivity
            startActivity(sendingIntent);

        });
        button2.setOnClickListener(v -> {

            // при нажатии кнопки забираем цвет
            sendingIntent.putExtra("picture", "tree2");
            //отправляем интент обратно в mainActivity
            startActivity(sendingIntent);

        });
        button3.setOnClickListener(v -> {

            // при нажатии кнопки забираем цвет
            sendingIntent.putExtra("picture", "tree3");
            //отправляем интент обратно в mainActivity
            startActivity(sendingIntent);

        });
        button4.setOnClickListener(v -> {

            // при нажатии кнопки забираем цвет
            sendingIntent.putExtra("picture", "tree4");
            //отправляем интент обратно в mainActivity
            startActivity(sendingIntent);

        });
        button5.setOnClickListener(v -> {

            // при нажатии кнопки забираем цвет
            sendingIntent.putExtra("picture", "tree5");
            //отправляем интент обратно в mainActivity
            startActivity(sendingIntent);

        });
        button6.setOnClickListener(v -> {

            // при нажатии кнопки забираем цвет
            sendingIntent.putExtra("picture", "tree6");
            //отправляем интент обратно в mainActivity
            startActivity(sendingIntent);

        });
    }
}