package com.example.cats.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cats.R;

public class CatsProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats_profile);

        Intent intent = getIntent();

        long catsID = intent.getLongExtra("CatsID", 0);
    }
}
