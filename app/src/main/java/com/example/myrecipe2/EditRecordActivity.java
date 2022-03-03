package com.example.myrecipe2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class EditRecordActivity extends AppCompatActivity {

    ActionBar actionBar;
    ImageView Fimagefood;
    EditText Fnamefood;
    EditText Fnamechef;
    EditText Fbahan;
    EditText Fstep;
    Button FsaveInfo;

    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int STORAGE_REQUEST_CODE = 101;

    public static final int IMAGE_PICK_CAMERA_CODE = 102;
    public static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;

    private String id,namefood, namechef, bahan,langkah, addtimeStamp,updateTimeStamp;
    private databaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Update Resep");
        Fimagefood = findViewById(R.id.foodImage);
        Fnamefood = findViewById(R.id.nameFood);
        Fnamechef = findViewById(R.id.nameChef);
        Fbahan = findViewById(R.id.ingeredients);
        Fstep = findViewById(R.id.cara);
        FsaveInfo = findViewById(R.id.buttonSimpan);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        namefood = intent.getStringExtra("NAMEFOOD");
        namechef = intent.getStringExtra("NAMECHEF");
        bahan = intent.getStringExtra("INGREDIENTS");
        langkah = intent.getStringExtra("STEP");
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        addtimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        Fnamefood.setText(namefood);
        Fnamechef.setText(namechef);
        Fbahan.setText(bahan);
        Fstep.setText(langkah);
        if(imageUri.toString().equals("null")){
            Fimagefood.setImageResource(R.drawable.ic_addphoto);
        }
        else{
            Fimagefood.setImageURI(imageUri);
        }

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        dbHelper = new databaseHelper(this);

        Fimagefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();
            }
        });
        FsaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        String nmfood = ""+Fnamefood.getText().toString().trim();
        String nmchef= ""+Fnamechef.getText().toString().trim();
        String bahan  = ""+Fbahan.getText().toString().trim();
        String step  = ""+Fstep.getText().toString().trim();

        String timeStamp = ""+System.currentTimeMillis();

        dbHelper.updateInfo(
                ""+id,
                ""+nmfood,
                ""+nmchef,
                ""+bahan,
                ""+step,
                ""+imageUri,
                ""+addtimeStamp,
                ""+timeStamp
        );
        doToast("Updated Succesfully! ");
    }

    private void imagePickDialog(){

        String[] options = {"Camera", "Gallery"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select for image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    if(!checkCameraPermissionGranted()){
                        requestCameraPermission();
                    }
                    else {
                        pickFromCamera();
                    }
                }
                else if (which==1){
                    if(!checkStoragePermissionGranted()){
                        requestStoragePermission();
                    }
                    else {
                        pickFromStorage();
                    }
                }

            }
        });
        builder.create().show();

    }

    private void pickFromStorage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_CAMERA_CODE);


    }

    private void pickFromCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image Title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Description");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);

    }

    private boolean checkStoragePermissionGranted(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);


    }

    private boolean checkCameraPermissionGranted(){
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result1 && result2;
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);


    }

    public void doToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST_CODE: {
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }

                    else {
                        doToast("Camera permission is required");
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE: {
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromStorage();
                    }
                    else {
                        doToast("Toast Permission required");
                    }
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }

            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK ){
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    Fimagefood.setImageURI(resultUri);
                }
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();
                    doToast(""+ error);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}