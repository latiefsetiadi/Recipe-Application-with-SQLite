package com.example.myrecipe2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class folder extends AppCompatActivity {

    ActionBar actionBar;
    RecyclerView mRecyclerView;
    databaseHelper DatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Folder");

        mRecyclerView = findViewById(R.id.recyclerView2);
        DatabaseHelper = new databaseHelper(this);

        showRecord();
    }
    private void showRecord() {

        Adapter2 adapter = new Adapter2(folder.this,DatabaseHelper.getAllData(Constans.C_ADD_TIMESTAMP + " DESC"));
            mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }

}