package com.pursuit.mypsychicapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AdapterViewOnItemSelectedListener {
    private FragmentManager fragmentManager;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        databaseHelper = new DatabaseHelper(getApplicationContext());
        showSpinnerFragment();
    }

    @Override
    public void showChoiceFragment(int choice) {
        ChoicesFragment fragment = new ChoicesFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("choice", choice);
        fragment.setArguments(arguments);
        fragmentManager
                .beginTransaction()
                // load our fragment into the container
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("button")
                .commit();
    }

    @Override
    public void showSpinnerFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, new SpinnerFragment())
                .addToBackStack("spinner")
                .commit();
    }

    @Override
    public void showResultFragment(int userChoice, int cpuChoice) {
        Bundle arguments = new Bundle();

        //check if the choices are correct
        if (userChoice == cpuChoice) {
            //add the choice to our database
            databaseHelper.addChoice(1);
            arguments.putBoolean("choice_correct", true);
        } else {
            databaseHelper.addChoice(0);
            arguments.putBoolean("choice_correct", false);
        }

        ResultsFragment fragment = new ResultsFragment();
        fragment.setArguments(arguments);
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("spinner")
                .commit();
    }
}
