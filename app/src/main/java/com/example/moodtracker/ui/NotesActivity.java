package com.example.moodtracker.ui;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.moodtracker.MainActivity;
import com.example.moodtracker.databinding.ActivityNotesBinding;


public class NotesActivity extends AppCompatActivity {

ActivityNotesBinding binding;

ImageButton okButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        okButton = binding.okNotes;

        okButton.setOnClickListener(v -> {


            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
