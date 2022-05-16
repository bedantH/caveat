package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class register_page extends AppCompatActivity {

    EditText name,email,password, cnfpassword;
    MaterialButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register_page);

        name = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        cnfpassword = findViewById(R.id.editTextConfirmPassword);
        register = findViewById(R.id.register);

        register.setOnClickListener(view -> {

            Snackbar.make(view, "Registration Successful", Snackbar.LENGTH_LONG).show();

            String name = register_page.this.name.getText().toString();
            String email = register_page.this.email.getText().toString();
            String password = register_page.this.password.getText().toString();
            String cnfpassword = register_page.this.cnfpassword.getText().toString();

            User user = new User();

            if(email.isEmpty()){
                Snackbar.make(findViewById(R.id.register_page),"Email is empty",Snackbar.LENGTH_SHORT).show();
            }
            else if(!email.contains("@")){
                Snackbar.make(findViewById(R.id.register_page),"Email is not valid",Snackbar.LENGTH_SHORT).show();
            }
            else if(!email.contains(".")){
                Snackbar.make(findViewById(R.id.register_page),"Email is not valid",Snackbar.LENGTH_SHORT).show();
            }
            else if(password.isEmpty()){
                Snackbar.make(findViewById(R.id.register_page),"Password is empty",Snackbar.LENGTH_SHORT).show();
            }
            else if(cnfpassword.isEmpty()){
                Snackbar.make(findViewById(R.id.register_page),"Confirm Password is empty",Snackbar.LENGTH_SHORT).show();
            }
            else if(!password.equals(cnfpassword)){
                Snackbar.make(findViewById(R.id.register_page),"Passwords does not match",Snackbar.LENGTH_SHORT).show();
            }
            else{
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                int result = dbHelper.insertUser(user);

                if (result == 1) {
                    Snackbar.make(findViewById(R.id.register_page), "User added successfully", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(findViewById(R.id.register_page), "User already exists", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}