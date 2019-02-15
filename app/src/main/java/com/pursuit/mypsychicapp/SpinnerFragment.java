package com.pursuit.mypsychicapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
public class SpinnerFragment extends Fragment {
    private static AdapterViewOnItemSelectedListener listener;

    public SpinnerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AdapterViewOnItemSelectedListener) {
            listener = (AdapterViewOnItemSelectedListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // you don't want to try to access any views here, those views aren't available until this method returns.
        return inflater.inflate(R.layout.fragment_spinner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] values = {"Shows", "Sports", "Season"};
        final Spinner spinner = view.findViewById(R.id.spinner);
        //the last parameter in createFromResource is the layout which defines what each item in the drop down looks like, similar to a recyclerview's viewholder layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_choices, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        Button submitbutton = view.findViewById(R.id.spinner_fragment_submit_button);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int choice = spinner.getSelectedItemPosition();
                listener.showChoiceFragment(choice);
            }
        });
    }
}
