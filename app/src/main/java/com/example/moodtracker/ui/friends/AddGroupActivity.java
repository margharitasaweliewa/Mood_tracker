package com.example.moodtracker.ui.friends;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moodtracker.MainActivity;
import com.example.moodtracker.R;
import com.example.moodtracker.databinding.ActivityAddGroupBinding;
import com.example.moodtracker.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class AddGroupActivity extends AppCompatActivity {

    ActivityAddGroupBinding binding;

    private List<String> contacts;

    private ListView listView;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 75;

    private SearchView searchView;

    private List<String> searchResult;

    private ImageButton imageButton;

    private List<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listView = findViewById(R.id.list_view);

        selectedItems = new ArrayList<>();

        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selectedItem = parent.getItemAtPosition(position).toString();

            if(!selectedItems.contains(selectedItem)) {
                selectedItems.add(selectedItem);
            } else {
                selectedItems.remove(selectedItem);
            }
        });

        showContacts();

        searchView = binding.searchBarContacts;

        searchResult = new ArrayList<>();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchResult = new ArrayList<>();

                for (String s:
                        contacts) {
                    if(s.contains(newText))
                        searchResult.add(s);
                }
                ArrayAdapter arrayAdapter =
                        new ArrayAdapter<>(getApplicationContext(),R.layout.list_item_add_group,searchResult);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

                return true;
            }
        });

        imageButton = binding.addGroupOk;

        imageButton.setOnClickListener(v -> {

            if (!binding.editTextGroup.getText().toString().equals("") && selectedItems.size() > 0){
                Intent intent = new Intent(this, MainActivity.class);

            //get selected items from list

                String[] contactsArr = new String[selectedItems.size()];

                selectedItems.toArray(contactsArr);

                intent.putExtra("contacts",contactsArr);
                intent.putExtra("groupName",binding.editTextGroup.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(this,
                        "Enter a group name and select some contacts",Toast.LENGTH_LONG).show();
            }
        });

    }


    private List<String> getContactList() {
        List<String> contacts = new ArrayList<>();
        // Get the ContentResolver
        ContentResolver cr = getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // Move the cursor to first. Also check whether the cursor is empty or not.
        if (cursor.moveToFirst()) {
            // Iterate through the cursor
            do {
                // Get the contacts name
                @SuppressLint("Range") String name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contacts.add(name);
            } while (cursor.moveToNext());
        }
        // Close the curosor
        cursor.close();

        return contacts;
    }

    private void showContacts(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }else {

            contacts = getContactList();

            listView.setAdapter(new ArrayAdapter<>(this,R.layout.list_item_add_group,contacts));
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setItemsCanFocus(false);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot " +
                        "display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
}