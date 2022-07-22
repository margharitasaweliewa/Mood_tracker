package com.example.moodtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.moodtracker.databinding.ActivitySharingBinding;
import com.example.moodtracker.databinding.FragmentFriendsBinding;
import com.example.moodtracker.ui.friends.CustomList;
import com.example.moodtracker.ui.friends.FriendsFragment;
import com.example.moodtracker.ui.statistics.StatisticsFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Sharing extends AppCompatActivity {


    ExpandableListView expandableListView;
    CustomList expandableListAdapter;
    List<String> expandableListTitle;
    public HashMap<String, List<String>> expandableListDetail;
    ActivitySharingBinding binding;

    List<String> contacts;

    String groupName;

    ToggleButton dailyColor;
    ImageButton okButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySharingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        View root = binding.getRoot();


        expandableListDetail = new HashMap<>();
        expandableListView = (ExpandableListView) binding.expandableListView;

        okButton = binding.addSharing;

        okButton.setOnClickListener(v -> {

            Toast.makeText(this,"Shared",Toast.LENGTH_LONG).show();

        });

        Intent recievingIntent = getIntent();

        if (recievingIntent.hasExtra("contacts")) {

            contacts = recievingIntent.getStringArrayListExtra("contacts");

            groupName = recievingIntent.getStringExtra("groupName");

            if (contacts != null) {
                expandableListDetail.put(groupName, contacts);
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                expandableListAdapter = new CustomList(this, expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
                expandableListAdapter.notifyDataSetChanged();
            }
        }

        if(recievingIntent.hasExtra("color")) {
            dailyColor = binding.buttonColorSharing;

            Drawable buttonDrawable = dailyColor.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);

            int color = recievingIntent.getIntExtra("color", 1);
            if (color == 1) {
                color = R.color.purple_700;
                DrawableCompat.setTint(buttonDrawable, getResources().getColor(color));
            } else {
                DrawableCompat.setTint(buttonDrawable, recievingIntent.getIntExtra("color",1));
            }

            dailyColor.setBackground(buttonDrawable);
        }

    }


    @Override
    public void onResume() {
        super.onResume();


    }

}