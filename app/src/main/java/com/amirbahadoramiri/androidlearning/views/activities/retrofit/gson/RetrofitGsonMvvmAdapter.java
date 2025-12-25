package com.amirbahadoramiri.androidlearning.views.activities.retrofit.gson;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.ItemGsonBinding;
import com.amirbahadoramiri.androidlearning.models.Character;

import java.util.List;

public class RetrofitGsonMvvmAdapter extends RecyclerView.Adapter<RetrofitGsonMvvmAdapter.MainHolder> {

    List<Character> characterList;

    public RetrofitGsonMvvmAdapter(List<Character> characters) {
        characterList = characters;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gson, parent, false));
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
        ItemGsonBinding itemGsonBinding;

        public MainHolder(@NonNull ItemGsonBinding itemGsonBinding) {
            super(itemGsonBinding.getRoot());
            this.itemGsonBinding = itemGsonBinding;
        }

        public void bindView(MainHolder holder) {
            itemGsonBinding.setData(characterList.get(holder.getAbsoluteAdapterPosition()));
        }
    }
}
