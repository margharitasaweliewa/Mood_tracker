package com.example.moodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.moodtracker.ui.friends.FriendsFragment;
import com.example.moodtracker.ui.statistics.StatisticsFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Sharing extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);
    }
    //Delete groups
    public void onClick(View v){
      v.setBackgroundResource(0);
    }
}