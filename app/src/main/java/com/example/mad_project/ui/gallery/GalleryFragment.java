package com.example.mad_project.ui.gallery;

import android.content.Intent;
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

import com.example.mad_project.CreateQuery;
import com.example.mad_project.DBHelper;
import com.example.mad_project.DisplayActivity;
import com.example.mad_project.MainActivity;
import com.example.mad_project.MainActivity2;
import com.example.mad_project.Query;
import com.example.mad_project.R;
import com.example.mad_project.databinding.FragmentGalleryBinding;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class GalleryFragment extends Fragment {
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        MaterialButton button = view.findViewById(R.id.postQuery);

        DBHelper dbHelper = new DBHelper(getContext());

        List<Query> allQueries = dbHelper.getQueries();

        for(int i = 0; i < allQueries.size(); i++){
            View card = LayoutInflater.from(getContext()).inflate(R.layout.card_layout, null);
            TextView query = card.findViewById(R.id.queryText);
            TextView user = card.findViewById(R.id.postedUserText);

            query.setText(allQueries.get(i).getQuery());
            user.setText(allQueries.get(i).getTitle());

            System.out.println("GalleryFragment:" + allQueries.get(i).getTitle());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CreateQuery.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}