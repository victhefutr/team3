package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {

    User userAuth;
    EditText username, password;
    Button signIn;
    TextView register;
    String user, pass;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //designated users for the app
        userAuth = new User();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        register = findViewById(R.id.register);

        //shared preferences
        sp = getSharedPreferences("appUserPrefs", Context.MODE_PRIVATE);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();

                SharedPreferences.Editor editor = sp.edit();

                boolean isValid = userAuth.getUser(user, pass);

                if(isValid){
                    User.storeUserDataLocally(editor, user);

                    Intent intent = new Intent(UserLogin.this, TrackApplication.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Login Failure", Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();
                userAuth.addUser(user, pass);
                username.setText("");
                password.setText("");
                Toast.makeText(getApplicationContext(), "Your registration is successfull", Toast.LENGTH_LONG).show();
            }
        });


    }
}