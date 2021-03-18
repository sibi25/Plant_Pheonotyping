package com.example.plant_pheonotyping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Gallery extends AppCompatActivity {

    ImageView home,image;
    Button btnInsert_Gallery,btnNext_Gallery;
    Uri imageUri;

    private static final int image_pick_code=1000;
    private static final int permission_code=1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        home=findViewById(R.id.Home);
        image=findViewById(R.id.imageView_Gallery);
        btnInsert_Gallery=findViewById(R.id.insert_Gallery);
        btnNext_Gallery=findViewById(R.id.next_Gallery);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InsertImage.class));
            }
        });

        btnNext_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),LeafCount.class);
                i.setData(imageUri);
                startActivity(i);
            }
        });

        btnInsert_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        String [] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};

                        requestPermissions(permissions,permission_code);

                    }
                    else {
                        pickImageFromGallery();

                    }
                }
                else {
                    pickImageFromGallery();

                }

            }
        });
    }

    private void pickImageFromGallery() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,image_pick_code);

         }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case permission_code:{
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }

            }



        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK && requestCode==image_pick_code){

            imageUri=data.getData();
            image.setImageURI(imageUri);
        }
    }


}

