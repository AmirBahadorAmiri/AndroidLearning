package com.amirbahadoramiri.androidlearning.models;

import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {
    private String name, image;

    public Character() {
    }

    public Character(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @BindingAdapter({"android:loadsrc"})
    public static void loadsrc(ImageView view, String url) {
        ViewPropertyTransition.Animator customAnimator = view2 -> {
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
        Glide.with(view.getContext())
                .load(url)
                .transition(GenericTransitionOptions.with(customAnimator))
                .into(view);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
