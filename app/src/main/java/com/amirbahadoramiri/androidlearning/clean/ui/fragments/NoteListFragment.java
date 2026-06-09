package com.amirbahadoramiri.androidlearning.clean.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.FragmentNotelistBinding;

public class NoteListFragment extends Fragment {

    FragmentNotelistBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notelist, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addNote.setOnClickListener(v -> addNote(0L));
    }

    public void addNote(Long id) {
        NavDirections directions = new ActionOnlyNavDirections(R.id.action_noteListFragment_to_noteFragment);
        directions.getArguments().putLong("id", id);
        Navigation.findNavController(binding.getRoot()).navigate(directions);
    }

}
