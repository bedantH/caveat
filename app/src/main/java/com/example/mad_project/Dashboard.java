package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.mad_project.ui.gallery.GalleryFragment;
import com.example.mad_project.ui.home.HomeFragment;
import com.example.mad_project.ui.slideshow.SlideshowFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_project.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView name,email;
    public String user_name = "User";
    public String user_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user_name = getIntent().getStringExtra("username");
        user_email = getIntent().getStringExtra("email");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_dashboard, new HomeFragment()).commit();

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.displayUserName);
        email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.displayUserEmail);

        name.setText(user_name);
        email.setText(user_email);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_gallery:
                fragment = new GalleryFragment();
                break;
            case R.id.nav_slideshow:
                fragment = new SlideshowFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_dashboard, fragment).commitNow();
        return true ;
    };

    public String getUsername(){
        return user_name;
    }
}