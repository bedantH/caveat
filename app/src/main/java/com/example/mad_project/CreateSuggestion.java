package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mad_project.ui.slideshow.SlideshowFragment;
import com.google.android.material.button.MaterialButton;

public class CreateSuggestion extends AppCompatActivity {

    ImageButton backButton;
    EditText suggestionTitle;
    EditText suggestionCourse;
    EditText suggestionTags;
    EditText suggestionMsg;
    CheckBox suggestionIsAnonymous;

    MaterialButton createSuggestionReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_suggestion);

        suggestionTitle = findViewById(R.id.suggestionTitle);
        suggestionCourse = findViewById(R.id.suggestionCourse);
        suggestionTags = findViewById(R.id.suggestionTags);
        suggestionMsg = findViewById(R.id.mainSuggestion);
        suggestionIsAnonymous = findViewById(R.id.isAnonymousSuggestion);

        backButton = findViewById(R.id.postBack);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        createSuggestionReq = findViewById(R.id.createSuggestionReq);
        createSuggestionReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAnonymousChecked = suggestionIsAnonymous.isChecked();
                String suggestionTitleText = suggestionTitle.getText().toString();
                String suggestionCourseText = suggestionCourse.getText().toString();
                String suggestionTagsText = suggestionTags.getText().toString();
                String suggestionMsgText = suggestionMsg.getText().toString();

                Suggestion suggestion = new Suggestion(suggestionTitleText, suggestionCourseText, suggestionTagsText, suggestionMsgText, isAnonymousChecked);

                DBHelper dbHelper = new DBHelper(CreateSuggestion.this);
                int result = dbHelper.insertSuggestion(suggestion);
                if (result == 1) {
                    finish();
                    Toast.makeText(CreateSuggestion.this, "Successfully inserted to database.", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(CreateSuggestion.this, "Error! Try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}