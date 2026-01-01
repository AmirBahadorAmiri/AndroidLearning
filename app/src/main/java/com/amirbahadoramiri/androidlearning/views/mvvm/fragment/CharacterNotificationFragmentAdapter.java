package com.amirbahadoramiri.androidlearning.views.mvvm.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.ItemMvvmFragmentRecyclerviewBinding;
import com.amirbahadoramiri.androidlearning.models.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterNotificationFragmentAdapter extends RecyclerView.Adapter<CharacterNotificationFragmentAdapter.MainHolder> {

    private List<Character> characterList = new ArrayList<>();

    public void setData(List<Character> characters) {
        if ( characterList == null ) characterList = new ArrayList<>();
        if ( !characterList.isEmpty() ) characterList.clear();
        characterList.addAll(characters);
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mvvm_fragment_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        holder.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        ItemMvvmFragmentRecyclerviewBinding binding;

        public MainHolder(@NonNull ItemMvvmFragmentRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(MainHolder holder) {
            binding.setData(characterList.get(holder.getAbsoluteAdapterPosition()));
        }
    }
}
