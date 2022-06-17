package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private String TAG = "SignupActivity";

    EditText etUsernameSignup;
    EditText etPasswordSignup;
    TextView tvLogin;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etUsernameSignup = findViewById(R.id.etUsernameSignup);
        etPasswordSignup = findViewById(R.id.etPasswordSignup);
        btnSignup = findViewById(R.id.btnSignup);
        tvLogin = findViewById(R.id.tvLogin);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(loginActivity);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Login Button");
                String username = etUsernameSignup.getText().toString();
                String password = etPasswordSignup.getText().toString();
                singUpUser(username, password);
            }
        });
    }

    private void singUpUser(String username, String password) {
        Log.i(TAG, "Attempting to sign up user: " + username);
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(SignupActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    goMainActivity();
                } else {
                    Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    ParseUser.logOut();
                }
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}