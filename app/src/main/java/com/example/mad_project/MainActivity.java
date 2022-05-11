package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    MaterialButton login;
    EditText editTextTextEmailAddress;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        login = (MaterialButton) findViewById(R.id.login);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = editTextTextEmailAddress.getText().toString();
                String password = editTextPassword.getText().toString();

                if (mail.equals("") && password.equals("")){
                    editTextTextEmailAddress.setBackground(getDrawable(R.drawable.edittext_outline_error));
                    editTextPassword.setBackground(getDrawable(R.drawable.edittext_outline_error));
                    Snackbar.make(findViewById(R.id.mainView), "Please fill all fields", Snackbar.LENGTH_LONG).show();
                }
                else if (mail.equals("")){
                    editTextTextEmailAddress.setBackground(getDrawable(R.drawable.edittext_outline_error));
                    Snackbar.make(findViewById(R.id.mainView), "Please enter email address", Snackbar.LENGTH_LONG).show();
                }
                else if (password.equals("")){
                    editTextPassword.setBackground(getDrawable(R.drawable.edittext_outline_error));
                    Snackbar.make(findViewById(R.id.mainView), "Please enter your password", Snackbar.LENGTH_LONG).show();
                } else{
                    editTextPassword.setBackground(getDrawable(R.drawable.edittext_outline_success));
                    editTextTextEmailAddress.setBackground(getDrawable(R.drawable.edittext_outline_success));
                    Snackbar.make(findViewById(R.id.mainView), "Logged In Successfully", Snackbar.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void registerPage(View view){
        Intent intent = new Intent(MainActivity.this, register_page.class);
        startActivity(intent);
    }
}