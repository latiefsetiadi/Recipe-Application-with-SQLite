package com.example.myrecipe2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class home extends AppCompatActivity {

    ActionBar actionBar;
    RecyclerView mRecyclerView;
    databaseHelper DatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Daftar Resep");

        mRecyclerView = findViewById(R.id.recyclerView);
        DatabaseHelper = new databaseHelper(this);

        showRecord();
    }

    private void showRecord() {

        Adapter adapter = new Adapter(home.this,DatabaseHelper.getAllData(Constans.C_ADD_TIMESTAMP + " DESC"));
            mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecord();
    }
}