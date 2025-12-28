package com.amirbahadoramiri.androidlearning.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character implements Parcelable {
    private String name, image;

    public Character() {
    }

    public Character(String name, String image) {
        this.name = name;
        this.image = image;
    }

    protected Character(Parcel in) {
        this.name = in.readString();
        this.image = in.readString();
    }

    @BindingAdapter({"android:loadsrc"})
    public static void loadsrc(ImageView view, String url) {

        ViewPropertyTransition.Animator customAnimator = view2 -> {
            view2.setAlpha(0f);
            view2.setScaleX(0.7f);
            view2.setScaleY(0.7f);

            view2.animate()
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.image);
    }

    // *** این متد برای ساختن آرایه از این آبجکت هست (به ندرت استفاده می‌شه) ***
    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

}
