package com.pursuit.mypsychicapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpinnerFragment fragment1 = new SpinnerFragment();
        FragmentManager manager1 = getSupportFragmentManager();

        manager1.beginTransaction().add(R.id.fragment_container,fragment1).commit();

        ShowChoiceFragment fragment2 = new ShowChoiceFragment();
        FragmentManager manager2 = getSupportFragmentManager();
        manager2.beginTransaction().add(R.id.fragment_container, fragment2).commit();
    }
}
