package com.pursuit.mypsychicapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultsFragment extends Fragment {
    private double percentage;
    private boolean isCorrect;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //get a reference to our helper class and get the percentage
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        percentage = databaseHelper.getPercentage();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isCorrect = getArguments().getBoolean("choice_correct");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView resultTextView = view.findViewById(R.id.results_textview);
        TextView percentageTextView = view.findViewById(R.id.percentage_textview);

        //display our results
        if (isCorrect) {
            resultTextView.setText(R.string.string_correct);
        } else {
            resultTextView.setText(R.string.string_wrong);
        }

        percentageTextView.setText(getString(R.string.results_string, percentage));
    }
}
