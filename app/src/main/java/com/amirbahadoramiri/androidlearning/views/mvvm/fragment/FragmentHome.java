package com.amirbahadoramiri.androidlearning.views.mvvm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.FragmentHomeBinding;

public class FragmentHome extends Fragment {

    private static FragmentHome instance;
    FragmentHomeBinding binding;

    public static FragmentHome getInstance() {
        if (instance == null) {
            instance = new FragmentHome();
            instance.setArguments(new Bundle());
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLogin.setOnClickListener(v -> {
//            Navigation.findNavController(binding.btnLogin).navigate(R.id.action_home_to_profile);


//            NavDirections directions = new ActionOnlyNavDirections(R.id.action_home_to_profile);
//            Navigation.findNavController(binding.btnLogin).navigate(directions);


            Bundle bundle = new Bundle();
            bundle.putString("key", "value");
            Navigation.findNavController(binding.btnLogin).navigate(R.id.action_home_to_profile, bundle);
        });

    }
}