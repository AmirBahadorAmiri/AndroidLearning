package com.amirbahadoramiri.androidlearning.views.activities.mvvm.livedata;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.ItemMvvmLivedataBinding;
import com.amirbahadoramiri.androidlearning.models.Character;

import java.util.ArrayList;
import java.util.List;

public class MvvmLiveDataAdapter extends RecyclerView.Adapter<MvvmLiveDataAdapter.MainHolder> {

    List<Character> characterList = new ArrayList<>();

    public void setData(List<Character> characters) {
        characterList.clear();
        addData(characters);
    }

    public void addData(List<Character> characters) {
        characterList.addAll(characters);
    }

    public List<Character> getData() {
        return characterList;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mvvm_livedata, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        holder.binding.setData(characterList.get(holder.getAbsoluteAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        ItemMvvmLivedataBinding binding;

        public MainHolder(@NonNull ItemMvvmLivedataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
