package com.example.moodtracker.ui.today;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moodtracker.BalanceWheelSettings;
import com.example.moodtracker.ColorMoodPicker;
import com.example.moodtracker.MainActivity;
import com.example.moodtracker.R;
import com.example.moodtracker.databinding.FragmentTodayBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class TodayFragment extends Fragment  {

    private FragmentTodayBinding binding;

    private Button createDailyPicture;
    private ImageButton wheelSettings;
    private Button colorButton;

    private TextView foodTextView;
    private TextView relaxTextView;
    private TextView sportTextView;
    private TextView sleepTextView;
    private TextView socialTextView;
    private TextView workTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTodayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.customDotsnet.init();

        //add listeners to buttons
        wheelSettings = binding.imageButton;

        wheelSettings.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), BalanceWheelSettings.class);

            foodTextView = binding.textViewFood;
            relaxTextView = binding.textViewRelax;
            sportTextView = binding.textViewSport;
            sleepTextView = binding.textViewSleep;
            socialTextView = binding.textViewSocial;
            workTextView = binding.textViewWork;


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
        });


        colorButton = binding.colorPicker;

        colorButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ColorMoodPicker.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        createDailyPicture = binding.dailypicture;
        //Описываем поведение при нажатии на кнопку
        createDailyPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
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

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        //получаем интент из другого активити
        Intent intent = getActivity().getIntent();


        if (intent.hasExtra("food")) {


            foodTextView = binding.textViewFood;
            relaxTextView = binding.textViewRelax;
            sportTextView = binding.textViewSport;
            sleepTextView = binding.textViewSleep;
            socialTextView = binding.textViewSocial;
            workTextView = binding.textViewWork;

            //устанавливаем текст
            foodTextView.setText(intent.getStringExtra("food"));
            relaxTextView.setText(intent.getStringExtra("relax"));
            sportTextView.setText(intent.getStringExtra("sport"));
            sleepTextView.setText(intent.getStringExtra("sleep"));
            socialTextView.setText(intent.getStringExtra("social"));
            workTextView.setText(intent.getStringExtra("work"));

        }
        //Добавила возвращение со страницы настроек цвета
        else if(intent.hasExtra("color"))
        {
            colorButton = binding.colorPicker;
            //Новый цвет присваиваю кнопке
            Drawable buttonDrawable = colorButton.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            //Надо проверить, чтобы оно срабатывало((((
            DrawableCompat.setTint(buttonDrawable, intent.getIntExtra("color", 1));
            colorButton.setBackground(buttonDrawable);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}