package com.example.myrecipe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class info extends AppCompatActivity {

    TextView deskrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        deskrip = findViewById(R.id.deskripsi);
        deskrip.setText("My Recipe adalah aplikasi berbasis CRUD dengan database SQLite yang bertujuan untuk" +
                " memudahkan user dalam menyimpan,mengedit dan menghapus resep.");
    }
}