package com.example.plant_pheonotyping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LeafCount extends AppCompatActivity {

    ImageView home,image;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_count);

        home=findViewById(R.id.Home);
        image=findViewById(R.id.Image);

        Intent i= getIntent();
        imageUri=i.getData();
        image.setImageURI(imageUri);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InsertImage.class));

            }
        });
    }
}
