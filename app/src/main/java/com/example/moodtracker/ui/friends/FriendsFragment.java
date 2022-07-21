package com.example.moodtracker.ui.friends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moodtracker.R;
import com.example.moodtracker.databinding.FragmentFriendsBinding;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendsFragment extends Fragment {

    private FragmentFriendsBinding binding;

    private ImageButton imageButton;

    ExpandableListView expandableListView;
    CustomList expandableListAdapter;
    List<String> expandableListTitle;
    public HashMap<String, List<String>> expandableListDetail;

    List<String> contacts;

    String groupName;

    ImageButton delete;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFriendsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        imageButton = binding.addGroup;

        imageButton.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(),AddGroupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        });

        expandableListDetail = new HashMap<>();

        expandableListView = (ExpandableListView) binding.expandableListView;
        //expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        //expandableListAdapter = new CustomList(getActivity(), expandableListTitle, expandableListDetail);
       // expandableListView.setAdapter(expandableListAdapter);



        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                Toast.makeText(getContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

                Toast.makeText(getContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Intent recievingIntent = getActivity().getIntent();

        if (recievingIntent.hasExtra("contacts")) {

            contacts = Arrays.asList(recievingIntent.getStringArrayExtra("contacts"));

            groupName = recievingIntent.getStringExtra("groupName");

            if (contacts != null) {
                expandableListDetail.put(groupName, contacts);
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                expandableListAdapter = new CustomList(getActivity(), expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
                expandableListAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "contacts is null", Toast.LENGTH_LONG).show();
            }
        }
    }
}
