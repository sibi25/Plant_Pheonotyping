package com.example.plant_pheonotyping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InsertImage extends AppCompatActivity {

    Button btnGallery_Home,btnCamera_Home,btnHistory_Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_image);

        btnGallery_Home=findViewById(R.id.gallery_Home);
        btnCamera_Home=findViewById(R.id.camera_Home);
        btnHistory_Home=findViewById(R.id.history_Home);

        btnGallery_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Gallery.class));
            }
        });

        btnCamera_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Camera.class));
            }
        });

        btnHistory_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),History.class));
            }
        });
    }
}
