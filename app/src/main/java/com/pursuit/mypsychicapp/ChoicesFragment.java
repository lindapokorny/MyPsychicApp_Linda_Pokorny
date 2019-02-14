package com.pursuit.mypsychicapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

public class ChoicesFragment extends Fragment implements View.OnClickListener {
    private AdapterViewOnItemSelectedListener listener;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private int cpuChoice;

    public ChoicesFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //this method is called when the fragment is attached to the parent Activity. The context parameter here is the activity the fragment is being attached to. If the parent activity is implementing this interface then we assign the listener to it. Allowing us to call the callback methods defined in the interface
        if(context instanceof AdapterViewOnItemSelectedListener){
            listener = (AdapterViewOnItemSelectedListener) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //assign the cpu choice to a random number from 1-4. Done in on create because this does not require any view interactions
        cpuChoice = new Random().nextInt(4) + 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_choice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView1 = view.findViewById(R.id.image_view_1);
        imageView2 = view.findViewById(R.id.image_view_2);
        imageView3 = view.findViewById(R.id.image_view_3);
        imageView4 = view.findViewById(R.id.image_view_4);

        //set the click listener on each image to this because the fragment implements the View.OnClickListener interface
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);

        //get the choice that was selected on the spinner in the previous fragment from the arguments bundle if the bundle is not null
        Bundle arguments = getArguments();
        if(arguments != null){
            int choice = getArguments().getInt("choice");
            showOptions(choice);
        }
    }

    private void showOptions(int choice) {
        switch(choice){
            case 1:
                // load images into image views
                break;
            case 2:
                //load images into image views
                break;
            case 3:
                //load images into image views
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.image_view_1:
                // tell our listener (MainActivity in this case) to display the results fragment based on this selection. Sending over the user's choice and the random choice we generated in onCreate.
                listener.showResultFragment(1, cpuChoice);
                break;
            case R.id.image_view_2:
                listener.showResultFragment(2, cpuChoice);
                break;
            case R.id.image_view_3:
                listener.showResultFragment(3, cpuChoice);
                break;
            case R.id.image_view_4:
                listener.showResultFragment(4, cpuChoice);
                break;
        }
    }
}
