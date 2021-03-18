package com.example.plant_pheonotyping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText email,password,cpassword;
    Button signUpBtn;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email=findViewById(R.id.email_SignUp);
        password=findViewById(R.id.password_SignUp);
        cpassword=findViewById(R.id.cPassword_SignUp);
        signUpBtn=findViewById(R.id.btnSignUp_SignUp);

        fAuth=FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String lEmail=email.getText().toString().trim();
                String lPassword=password.getText().toString().trim();
                String lcPassword=cpassword.getText().toString().trim();

                if (TextUtils.isEmpty(lEmail)) {
                    email.setError("Email is Required!");
                    return;
                }

                if (TextUtils.isEmpty(lPassword)){
                    password.setError("Password is Required");
                    return;
                }
                if (!(lPassword.equals(lcPassword))){
                    cpassword.setError("Password Didn't Match");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(lEmail,lPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(SignUp.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
    }
}
