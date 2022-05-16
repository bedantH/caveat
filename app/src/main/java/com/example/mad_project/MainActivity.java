package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        login = (MaterialButton) findViewById(R.id.login);
        editTextTextEmailAddress = (EditText) findViewById(R.id.loginEmail);
        editTextPassword = (EditText) findViewById(R.id.loginPassword);


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

                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    Cursor user = dbHelper.checkEmailAndPassword(mail, password);

                    if (user.getCount() == 0){
                        Snackbar.make(findViewById(R.id.mainView), "Invalid email or password", Snackbar.LENGTH_LONG).show();
                    } else{
                        System.out.println("Success");
//                        get the username from database
                        user.moveToFirst();
                        username = user.getString(user.getColumnIndex("Name"));
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        intent.putExtra("username", username);
                        intent.putExtra("email", mail);
                        startActivityForResult(intent, 1);
                    }
                }
            }
        });
    }

    public void registerPage(View view){
        Intent intent = new Intent(MainActivity.this, register_page.class);
        startActivity(intent);
    }
}