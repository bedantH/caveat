package com.example.mad_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DisplayActivity extends AppCompatActivity {

    TextView text;
    TextView userText;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);

        text = findViewById(R.id.mainQueryDisplayText);
        userText = findViewById(R.id.userNameDisplayText);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

    }

    private void getAndSetIntentData() {
        if (getIntent() != null) {
            String query = getIntent().getStringExtra("queryMsg");
            String user = getIntent().getStringExtra("queryTitle");
            title = getIntent().getStringExtra("queryTags");
            text.setText(query);
            userText.setText(user);

            Log.d("DisplayActivity", "getAndSetIntentData: " + query);
        }else{
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }
    }
}