package com.example.myapplication.ui.listtrip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.databinding.FragmentListTripBinding;
import com.example.myapplication.databinding.FragmentNewtripBinding;

public class ListTripFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_newtrip, container, false);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public void etFilterTrip(){
        EditText etFilterTrip = getActivity().findViewById(R.id.etFilterTrip);

        etFilterTrip.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(getActivity(), "Searching", Toast.LENGTH_SHORT).show();
            }
        });
    }
}