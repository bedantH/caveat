package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class CreateQuery extends AppCompatActivity {

    ImageButton backBtn;
    CheckBox isAnonymous;
    EditText queryTitle;
    EditText queryCourse;
    EditText tags;
    EditText mainQuery;
    MaterialButton createQueryReq;

    Boolean isAnonymousChecked = false;
    String queryTitleText;
    String queryCourseText;
    String tagsText;
    String mainQueryText;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_query);

        dbHelper = new DBHelper(this);

        isAnonymous = findViewById(R.id.isAnonymous);
        queryTitle = findViewById(R.id.queryTitle);
        queryCourse = findViewById(R.id.queryCourse);
        tags = findViewById(R.id.tags);
        mainQuery = findViewById(R.id.mainQuery);
        createQueryReq = findViewById(R.id.createQueryReq);

        createQueryReq.setOnClickListener(v -> {

            isAnonymousChecked = isAnonymous.isChecked();
            queryTitleText = queryTitle.getText().toString();
            queryCourseText = queryCourse.getText().toString();
            tagsText = tags.getText().toString();
            mainQueryText = mainQuery.getText().toString();

            Query query = new Query(queryTitleText, queryCourseText, tagsText, mainQueryText, isAnonymousChecked);

            int result = dbHelper.insertQuery(query);
            if (result == 1) {
                Toast.makeText(this, "Query Created", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Query Not Created", Toast.LENGTH_SHORT).show();
            }
        });

        backBtn = findViewById(R.id.postBack);

        backBtn.setOnClickListener(v -> {
            finish();
        });

    }
}