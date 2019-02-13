package com.pursuit.mypsychicapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpinnerFragment extends Fragment implements AdapterViewOnItemSelectedListener {
    private View rootView;


    public SpinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String [] values =
                {"Shows", "Sports","Season"};
        Spinner spinner = rootView.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_choices, R.layout.fragment_spinner);
        spinner.setAdapter(spinnerAdapter);

        Button submitbutton = rootView.findViewById(R.id.spinner_fragment_submit_button);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowChoiceFragment pictureFragment = new ShowChoiceFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_activity_layout, pictureFragment).addToBackStack("button");
                fragmentTransaction.commit();


            }
        });
        return rootView = inflater.inflate(R.layout.fragment_spinner, container, false);
    }

}
