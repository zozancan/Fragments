package com.zozancan.fragments;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListFragment.ItemSelected {

    TextView tvDescription;
    String[] descriptions;

    Fragment lf;
    Fragment ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDescription = findViewById(R.id.tvDescription);

        descriptions = getResources().getStringArray(R.array.descriptions);

        //the phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {
            loadFragment(R.id.listFragment, R.id.detailFragment, true);
        }

        //the phone is in landscape mode
        if (findViewById(R.id.layout_land) != null) {
            loadFragment(R.id.listFragment, R.id.detailFragment, false);
        }
    }


    public void loadFragment(@IdRes int firstFragment, @IdRes int lastFragment, boolean isPortrait) {
        FragmentManager manager = this.getSupportFragmentManager();
        ff = manager.findFragmentById(firstFragment);
        lf = manager.findFragmentById(lastFragment);
        if (ff != null && lf != null) {
            FragmentTransaction fragmentTransaction = manager.beginTransaction();

            if (isPortrait) {
                fragmentTransaction.hide(lf);
            } else {
                fragmentTransaction.show(lf);
            }
            fragmentTransaction.show(ff);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onItemSelected(int index) {
        tvDescription.setText(descriptions[index]);

        //the phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {
            loadFragment(R.id.detailFragment, R.id.listFragment, true);
        }
    }

    @Override
    public void onBackPressed() {
        if (ff instanceof DetailFragment) {
            if (!(ff.isHidden())) {
                loadFragment(R.id.listFragment, R.id.detailFragment, true);
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
