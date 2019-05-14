package com.zozancan.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListFragment.ItemSelected {

    TextView tvDescription;
    String [] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDescription = findViewById(R.id.tvDescription);

        descriptions = getResources().getStringArray(R.array.descriptions);

        //the phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.detailFragment))
                    .show(manager.findFragmentById(R.id.listFragment))
                    .commit();

        }

        //the phone is in landscape mode
        if (findViewById(R.id.layout_land) != null) {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.listFragment))
                    .show(manager.findFragmentById(R.id.detailFragment))
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int index) {
        tvDescription.setText(descriptions[index]);

        //the phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailFragment))
                    .hide(manager.findFragmentById(R.id.listFragment))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
