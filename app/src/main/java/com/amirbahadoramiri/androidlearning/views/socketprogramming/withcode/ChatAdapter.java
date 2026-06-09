package com.amirbahadoramiri.androidlearning.views.socketprogramming.withcode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    private List<String> data = new ArrayList<>();

    public void addChat(String chat) {
        data.add(chat);
        notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        ((TextView) holder.itemView.findViewById(android.R.id.text1)).setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        public ChatHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
