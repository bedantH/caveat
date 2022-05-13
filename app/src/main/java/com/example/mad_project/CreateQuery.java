package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

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

//            dbHelper.insertQuery(queryTitleText, queryCourseText, tagsText, mainQueryText, isAnonymousChecked);
        });

        backBtn = findViewById(R.id.postBack);

        backBtn.setOnClickListener(v -> {
            finish();
        });

    }
}