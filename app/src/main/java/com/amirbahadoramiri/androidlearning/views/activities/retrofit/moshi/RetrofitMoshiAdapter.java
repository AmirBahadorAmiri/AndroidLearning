package com.amirbahadoramiri.androidlearning.views.activities.retrofit.moshi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.models.Character;
import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class RetrofitMoshiAdapter extends RecyclerView.Adapter<RetrofitMoshiAdapter.MainHolder> {

    List<Character> characterList;

    public RetrofitMoshiAdapter(List<Character> characters) {
        characterList = characters;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ion, parent, false));
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

        SimpleDraweeView my_image_view;
        AppCompatTextView textview;

        public MainHolder(@NonNull View itemView) {
            super(itemView);
            my_image_view = itemView.findViewById(R.id.my_image_view);
            textview = itemView.findViewById(R.id.textview);
        }

        public void bindView(MainHolder holder) {

            ViewPropertyTransition.Animator customAnimator = view -> {
                view.setAlpha(0f);
                view.setScaleX(0.7f);
                view.setScaleY(0.7f);

                view.animate()
                        .alpha(1f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(800)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
            };

            textview.setText(characterList.get(holder.getAbsoluteAdapterPosition()).getName());
            Glide.with(itemView).load(characterList.get(holder.getAbsoluteAdapterPosition()).getImage()).transition(GenericTransitionOptions.with(customAnimator)).into(my_image_view);
        }
    }
}
