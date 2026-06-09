package com.amirbahadoramiri.androidlearning.clean.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.clean.core.data.Note;
import com.amirbahadoramiri.androidlearning.clean.framework.NoteViewModel;
import com.amirbahadoramiri.androidlearning.databinding.FragmentNoteBinding;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class NoteFragment extends Fragment {

    FragmentNoteBinding binding;
    private NoteViewModel noteViewModel;
    private Note currentNote = new Note(0L,"", "", 0L, 0L);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        binding.saveNote.setOnClickListener(v -> {
            String title = binding.noteTitle.getText().toString();
            String content = binding.noteContent.getText().toString();
            if (!(title.isEmpty() || content.isEmpty())) {
                Long time = System.currentTimeMillis();
                currentNote.setTitle(title);
                currentNote.setContent(content);
                currentNote.setUpdatedTime(time);
                if (currentNote.getId() == 0) {
                    currentNote.setCreatedTime(time);
                }
                noteViewModel.saveNoteRx(currentNote)
                        .subscribe();
            } else {
                Toast.makeText(requireContext(), "You Cannt Saved Nullable Notes", Toast.LENGTH_SHORT).show();
            }
        });

        observeViewModel();

    }

    private void observeViewModel() {

        noteViewModel.saved.observe(requireActivity(), aBoolean -> {
            if ( aBoolean ) {
                Toast.makeText(requireContext(), "Done!", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(binding.getRoot()).popBackStack();
            } else {
                Toast.makeText(requireContext(), "Somethings was wrong, Please try again", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }
        });

    }

}
