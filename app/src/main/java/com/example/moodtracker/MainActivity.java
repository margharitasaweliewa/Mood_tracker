package com.example.moodtracker;



import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moodtracker.databinding.ActivityMainBinding;
import com.example.moodtracker.ui.friends.FriendsFragment;
import com.example.moodtracker.ui.statistics.StatisticsFragment;
import com.example.moodtracker.ui.today.TodayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

public class MainActivity extends AppCompatActivity  {
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

        TodayFragment todayFragment = new TodayFragment();


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.navView.setSelectedItemId(R.id.navigation_today);
        replaceFragment(todayFragment);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       /* AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_friends, R.id.navigation_today, R.id.navigation_statistics)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/
        //Инициализируем поля
        colorPicker = findViewById(R.id.colorPicker);
        createDailyPicture = findViewById(R.id.dailypicture);
        //CustomActivityChart customActivityChart = new CustomActivityChart(this,null);
        //CustomDotsNet customDotsNet = new CustomDotsNet(this,null);

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

    }


    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();


    }

    /*ивент срабатывает когда мы возвращаемся в этот активити из другого
     * https://developer.android.com/guide/components/activities/activity-lifecycle
     *
     * */
    @Override
    protected void onResume() {
        super.onResume();


    }


}