package com.example.mad_project.ui.gallery;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.CreateQuery;
import com.example.mad_project.CustomAdapter;
import com.example.mad_project.DBHelper;
import com.example.mad_project.DisplayActivity;
import com.example.mad_project.MainActivity;
import com.example.mad_project.MainActivity2;
import com.example.mad_project.Query;
import com.example.mad_project.R;
import com.example.mad_project.databinding.FragmentGalleryBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    private FragmentGalleryBinding binding;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<String> queryTitle, queryCourse, queryTags, queryMsg, isAnonymous;
    CustomAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        FloatingActionButton button = view.findViewById(R.id.postQuery);
        recyclerView = view.findViewById(R.id.recyclerView);

        dbHelper = new DBHelper(getContext());

        queryTitle = new ArrayList<>();
        queryCourse = new ArrayList<>();
        queryTags = new ArrayList<>();
        queryMsg = new ArrayList<>();
        isAnonymous = new ArrayList<>();

        storeData();

        System.out.println("queryTitle: " + queryTitle);
        System.out.println("queryCourse: " + queryCourse);
        System.out.println("queryTags: " + queryTags);
        System.out.println("queryMsg: " + queryMsg);
        System.out.println("isAnonymous: " + isAnonymous);

        adapter = new CustomAdapter(getContext(), getActivity(), queryTitle, queryCourse, queryTags, queryMsg, isAnonymous);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CreateQuery.class);
                startActivity(intent);
            }
        });
    }

    void storeData(){
        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                queryTitle.add(cursor.getString(1));
                queryCourse.add(cursor.getString(2));
                queryTags.add(cursor.getString(3));
                queryMsg.add(cursor.getString(4));
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