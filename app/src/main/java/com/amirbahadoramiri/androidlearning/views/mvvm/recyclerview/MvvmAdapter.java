package com.amirbahadoramiri.androidlearning.views.mvvm.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.ItemMvvmRecyclerviewBinding;
import com.amirbahadoramiri.androidlearning.models.User;

import java.util.ArrayList;
import java.util.List;

public class MvvmAdapter extends RecyclerView.Adapter<MvvmAdapter.MvvmViewHolder> {

    List<User> userTestClassList;

    public void addData(List<User> newList) {
        if (userTestClassList == null) userTestClassList = new ArrayList<>();
        userTestClassList.addAll(newList);
    }

    @NonNull
    @Override
    public MvvmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MvvmViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mvvm_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MvvmViewHolder holder, int position) {
        holder.onBind(holder);
    }

    @Override
    public int getItemCount() {
        return userTestClassList.size();
    }

    public class MvvmViewHolder extends RecyclerView.ViewHolder {

        ItemMvvmRecyclerviewBinding binding;

        public MvvmViewHolder(@NonNull ItemMvvmRecyclerviewBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(MvvmViewHolder holder) {
            binding.setUser(userTestClassList.get(holder.getAbsoluteAdapterPosition()));
        }
    }
}
