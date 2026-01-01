package com.amirbahadoramiri.androidlearning.views.retrofit.jackson;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.ItemJaksonBinding;
import com.amirbahadoramiri.androidlearning.models.Character;

import java.util.List;

public class RetrofitJacksonAdapter extends RecyclerView.Adapter<RetrofitJacksonAdapter.MainHolder> {

    List<Character> characterList;

    public RetrofitJacksonAdapter(List<Character> characters) {
        characterList = characters;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_jakson, parent, false));
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

        ItemJaksonBinding binding;

        public MainHolder(@NonNull ItemJaksonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(MainHolder holder) {
            binding.setData(characterList.get(holder.getAbsoluteAdapterPosition()));
        }
    }
}