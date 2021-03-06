package com.example.mad_project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mad_project.DBHelper;
import com.example.mad_project.Dashboard;
import com.example.mad_project.R;
import com.example.mad_project.databinding.FragmentHomeBinding;
import com.example.mad_project.ui.gallery.GalleryFragment;
import com.example.mad_project.ui.slideshow.SlideshowFragment;

public class HomeFragment extends Fragment {

    String user_name;
    TextView dispUsername;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Dashboard dashboard = new Dashboard();
        user_name = dashboard.getUsername();

        return root;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        CardView queriesCard = view.findViewById(R.id.bottomCard);
        CardView suggestionCard = view.findViewById(R.id.bottom1_card);

        TextView query = view.findViewById(R.id.queryCount);
        TextView suggestion = view.findViewById(R.id.suggestionCount);
        dispUsername = view.findViewById(R.id.displayUserNameDashboard);

        dispUsername.setText(user_name);

        DBHelper db = new DBHelper(getContext());
        query.setText(String.valueOf(db.getCountOfQueries()));
        suggestion.setText(String.valueOf(db.getCountOfSuggestions()));

        suggestionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_dashboard, new SlideshowFragment()).commit();

            }
        });

        queriesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_dashboard, new GalleryFragment()).commit();
            }
        });
    }

}