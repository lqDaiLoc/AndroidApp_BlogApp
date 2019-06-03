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

import java.util.Set;

public class Register_Activity extends AppCompatActivity {
    private EditText register_email;
    private EditText register_password;
    private EditText register_confirm_password;
    private Button register_btn;
    private Button register_login_btn;
    private ProgressBar register_progress;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        mAuth = FirebaseAuth.getInstance();


        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_confirm_password = findViewById(R.id.register_comfirm_password);
        register_btn = findViewById(R.id.register_btn);
        register_login_btn = findViewById(R.id.register_login_btn);
        register_progress = findViewById(R.id.register_progress);

        register_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogin = new Intent(Register_Activity.this, LoginActivity.class);
                startActivity(intentToLogin);
            }
        });


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = register_email.getText().toString();
                String pass = register_password.getText().toString();
                String comfirm_pass = register_confirm_password.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(comfirm_pass))
                {
                    if (pass.equals(comfirm_pass))
                    {
                        register_progress.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent setupIntent = new Intent(Register_Activity.this, SetupActivity.class);
                                    startActivity(setupIntent);
                                    finish();

                                }
                                else
                                {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(Register_Activity.this, "Error: " + error, Toast.LENGTH_LONG).show();

                                }

                                register_progress.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(Register_Activity.this, "Confirm Password and Password doesn't match!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            sendToMain();
        }
    }

    private void sendToMain() {

        Intent mainInten = new Intent(Register_Activity.this, MainActivity.class);
        startActivity(mainInten);
        finish();
    }
}
