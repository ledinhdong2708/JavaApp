package com.example.myapplication.ui.listtrip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.databinding.FragmentListTripBinding;
import com.example.myapplication.databinding.FragmentNewtripBinding;

public class ListTripFragment extends Fragment {

    private @NonNull FragmentListTripBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListTripViewModel listTripViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ListTripViewModel.class);

        binding = FragmentListTripBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}