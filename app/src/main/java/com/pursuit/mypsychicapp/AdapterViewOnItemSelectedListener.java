package com.pursuit.mypsychicapp;

//define methods for your activity to implement to show the appropriate fragment. This delegation is important so our fragments aren't inflating each other. Keeping our components decoupled.
interface AdapterViewOnItemSelectedListener {
    void showChoiceFragment(int choice);
    void showSpinnerFragment();
    //pass in the choice made in the choice fragment to the result fragment
    void showResultFragment(int userChoice, int cpuChoice);
}
