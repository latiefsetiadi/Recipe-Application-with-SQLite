package com.example.myrecipe2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail_Activity extends AppCompatActivity {

    ActionBar actionBar;
    TextView nmrsp,nmchf;
    EditText bahan,Step;
    ImageView img;
    private Uri imageUri;
    String namafood,namechef,Ingredients,langkah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Resep");
        nmrsp = findViewById(R.id.textResep);
        nmchf = findViewById(R.id.nmchef);
        bahan = findViewById(R.id.ingeredients);
        Step = findViewById(R.id.step);
        img = findViewById(R.id.foodImage);
        Intent inten = getIntent();
        namafood = inten.getStringExtra("NAMEFOOD");
        namechef = inten.getStringExtra("NAMECHEF");
        Ingredients = inten.getStringExtra("INGREDIENTS");
        langkah = inten.getStringExtra("STEP");
        imageUri = Uri.parse(inten.getStringExtra("IMAGE"));
        nmrsp.setText(""+namafood);
        nmchf.setText(""+namechef);
        bahan.setText(""+Ingredients);
        Step.setText(""+langkah);
        if(imageUri.toString().equals("null")){
            img.setImageResource(R.drawable.ic_addphoto);
        }
        else{
            img.setImageURI(imageUri);
        }
    }
}