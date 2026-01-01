package com.amirbahadoramiri.androidlearning.views.mvvm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.FragmentProfileBinding;

public class FragmentProfile extends Fragment {

    private static FragmentProfile instance;
    FragmentProfileBinding binding;

    public static FragmentProfile getInstance() {
        if ( instance == null ) {
            instance = new FragmentProfile();
            instance.setArguments(new Bundle());
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txt.setText(requireArguments().getString("key"));

    }
}
