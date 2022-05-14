package com.example.mad_project.ui.slideshow;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.CreateQuery;
import com.example.mad_project.CreateSuggestion;
import com.example.mad_project.DBHelper;
import com.example.mad_project.DisplayActivity;
import com.example.mad_project.R;
import com.example.mad_project.databinding.FragmentGalleryBinding;
import com.example.mad_project.databinding.FragmentSlideshowBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<String> suggestionTitle, suggestionCourse, suggestionTags, suggestionMsg, isAnonymous;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.fragment_slideshow, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        FloatingActionButton button = view.findViewById(R.id.postSuggestion);
        recyclerView = view.findViewById(R.id.recyclerViewSuggestion);

        dbHelper = new DBHelper(getContext());

        suggestionTitle = new ArrayList<>();
        suggestionCourse = new ArrayList<>();
        suggestionTags = new ArrayList<>();
        suggestionMsg = new ArrayList<>();
        isAnonymous = new ArrayList<>();

        storeData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CreateSuggestion.class);
                startActivity(intent);
            }
        });
    }

    void storeData(){
        Cursor cursor = dbHelper.readAllSuggestions();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                suggestionTitle.add(cursor.getString(1));
                suggestionCourse.add(cursor.getString(2));
                suggestionTags.add(cursor.getString(3));
                suggestionMsg.add(cursor.getString(4));
                isAnonymous.add(cursor.getString(0));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}