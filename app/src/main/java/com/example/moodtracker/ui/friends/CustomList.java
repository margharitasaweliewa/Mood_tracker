package com.example.moodtracker.ui.friends;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.moodtracker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CustomList extends BaseExpandableListAdapter {

    private HashMap<String, List<String>> listAdapter;

    private Context context;

    private List<String> groupTitles;

    public List<ImageView> deleteButtons;

    public CustomList(Context context, List<String> groupTitles,
                                       HashMap<String, List<String>> listAdapter) {
        this.context = context;
        this.groupTitles = groupTitles;
        this.listAdapter = listAdapter;
        deleteButtons = new ArrayList<>();
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.listAdapter.get(this.groupTitles.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);

        ImageView delete = convertView.findViewById(R.id.delete_item);
        delete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Do you sure you want delete an item?").setTitle("Delete");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    List<String> vals = new ArrayList<>(listAdapter.get(groupTitles.get(listPosition)));
                    vals.remove(expandedListPosition);
                    listAdapter.remove(groupTitles.get(listPosition));
                    listAdapter.put(groupTitles.get(listPosition),vals);
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());

            AlertDialog dialog = builder.create();

            dialog.show();
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.listAdapter.get(this.groupTitles.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.groupTitles.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.groupTitles.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        ImageView delete = convertView.findViewById(R.id.delete_group);
        delete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Do you sure you want delete a group?").setTitle("Delete");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    List<String> keys =  new ArrayList<>(listAdapter.keySet());
                    String key = keys.get(listPosition);
                    listAdapter.remove(groupTitles.get(listPosition));
                    groupTitles.remove(listPosition);
                    notifyDataSetChanged();

                }
            });
            builder.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());

            AlertDialog dialog = builder.create();

            dialog.show();
        });

        ImageView add = convertView.findViewById(R.id.add_member);
        add.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Do you sure you want to add member?").setTitle("Delete");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context, AddGroupActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("GroupEditName", groupTitles.get(listPosition));
                    ArrayList<String> res =
                            new ArrayList<>(listAdapter.get(groupTitles.get(listPosition)));
                    intent.putStringArrayListExtra("GroupEditList", res);
                    context.startActivity(intent);
                }
            });
            builder.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());

            AlertDialog dialog = builder.create();

            dialog.show();
        });

        deleteButtons.add(delete);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
