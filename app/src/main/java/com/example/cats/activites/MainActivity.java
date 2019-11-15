package com.example.cats.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.cats.R;
import com.example.cats.fragments.FavouriteRecyclerFragment;
import com.example.cats.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
//    ImageButton searchButton;
//    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        searchButton = (ImageButton) findViewById(R.id.searchButton);
//        searchText = (EditText) (findViewById(R.id.search));


//        searchButton.setOnClickListener(new View.OnClickListener()
//                {
//                    public void onClick(View view) {
//                        String searchResult = searchText.getText().toString();
//                    }
//                });

//        gets the string inputted and which is used to filter users search



        Fragment fragment = new SearchFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.nav_fav) {
                Fragment fragment = new FavouriteRecyclerFragment();
                swapFragment(fragment);
                return true;
            } else if (menuItem.getItemId() == R.id.nav_search) {
                Fragment fragment = new SearchFragment();
                swapFragment(fragment);
                return true;
            }
            return false;
        }
        });
    }


    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }
}
