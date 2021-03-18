package com.example.plant_pheonotyping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button sign_UpBtn,sign_InBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.email_SignIn);
        password=findViewById(R.id.password_SignIn);
        sign_InBtn=findViewById(R.id.btn_SignIn);
        sign_UpBtn=findViewById(R.id.btnSignUp_SignIn);
        fAuth=FirebaseAuth.getInstance();

        sign_InBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lEmail=email.getText().toString().trim();
                String lPassword=password.getText().toString().trim();

                if (TextUtils.isEmpty(lEmail)) {
                    email.setError("Email is Required!");
                    return;
                }

                if (TextUtils.isEmpty(lPassword)){
                    password.setError("Password is Required");
                    return;
                }


            fAuth.signInWithEmailAndPassword(lEmail,lPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Logged In Successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),InsertImage.class));
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            });

            }
        });


        sign_UpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });


    }
}
