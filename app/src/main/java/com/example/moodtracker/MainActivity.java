package com.example.moodtracker;



import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moodtracker.databinding.ActivityMainBinding;
import com.example.moodtracker.ui.friends.FriendsFragment;
import com.example.moodtracker.ui.statistics.StatisticsFragment;
import com.example.moodtracker.ui.today.TodayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    //Кнопка для выбора цвета
    private Button colorPicker;
    private ActivityMainBinding binding;
    private static final int firstId = 1;
    private ImageButton createDailyPicture;
// id нужны для реализации метода onColorSelected

    //поля для TextView
    private TextView foodTextView;
    private TextView relaxTextView;
    private TextView sportTextView;
    private TextView sleepTextView;
    private TextView socialTextView;
    private TextView workTextView;


    private List<String> contacts;
    private String groupName;
    private int color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TodayFragment todayFragment = new TodayFragment();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.navView.setSelectedItemId(R.id.navigation_today);
        replaceFragment(todayFragment);

        contacts = new ArrayList<>();




        binding.navView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.navigation_friends:
                    replaceFragment(new FriendsFragment());
                    break;
                case  R.id.navigation_today:
                    replaceFragment(todayFragment);
                    break;
                case R.id.navigation_statistics:
                    replaceFragment(new StatisticsFragment());
                    break;

            }

            return true;
        });

        Intent recievingIntent = getIntent();
        if (recievingIntent != null) {

            if (recievingIntent.hasExtra("contacts")) {
                contacts = Arrays.asList(recievingIntent.getStringArrayExtra("contacts"));
                groupName = recievingIntent.getStringExtra("groupName");
                replaceFragment(new FriendsFragment());
                binding.navView.setSelectedItemId(R.id.navigation_friends);

            } else if(recievingIntent.hasExtra("color")) {

               color = recievingIntent.getIntExtra("color", 1);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.top_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.shareButton:

                Intent intent = new Intent(this, Sharing.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ArrayList<String> res = new ArrayList<>(contacts);
                intent.putStringArrayListExtra("contacts",res);
                intent.putExtra("groupName",groupName);
                intent.putExtra("color", color);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();


    }


    @Override
    protected void onResume() {
        super.onResume();

    }


}