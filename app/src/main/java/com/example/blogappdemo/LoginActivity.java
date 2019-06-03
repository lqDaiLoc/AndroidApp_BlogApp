package com.example.blogappdemo;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginEmail, edtLoginPass;
    private Button btnLogin, btnRegister;
    private  FirebaseAuth mAuth;
    private ProgressBar loginProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        loginProgress = findViewById(R.id.login_progress);
        btnLogin = findViewById(R.id.login_btn);
        btnRegister = findViewById(R.id.reg_btn);
        edtLoginEmail = findViewById(R.id.login_email);
        edtLoginPass = findViewById(R.id.login_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regintent = new Intent(LoginActivity.this, Register_Activity.class);
                startActivity(regintent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = edtLoginEmail.getText().toString();
                String passwordEmail = edtLoginPass.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(passwordEmail))
                {

                    loginProgress.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail, passwordEmail).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {SentToMain();


                            }
                            else
                            {
                                String e = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error "+e, Toast.LENGTH_LONG).show();
                            }


                            loginProgress.setVisibility(View.INVISIBLE);
                        }
                    });
                }
                else
                    Toast.makeText(LoginActivity.this, "Please fill all information!", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null)
        {
            SentToMain();
        }
    }

    private void SentToMain() {


        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
