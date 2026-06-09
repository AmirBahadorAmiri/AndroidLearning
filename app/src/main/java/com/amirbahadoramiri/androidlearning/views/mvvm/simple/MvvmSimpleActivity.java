package com.amirbahadoramiri.androidlearning.views.mvvm.simple;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityMvvmSimpleBinding;
import com.amirbahadoramiri.androidlearning.models.User;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

import java.util.Objects;

public class MvvmSimpleActivity extends BaseActivity implements TextWatcher {

    ActivityMvvmSimpleBinding binding;
    User user;
    String str = "null string";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm_simple);
        setViewCompat();

        Logger.debug(str);

//        User user = new User("HassanKachal","HassanKachal@gmail.com");
//        if ( savedInstanceState!= null ) {
//            user.setEmail(savedInstanceState.getString("key"));
//        }

        if ( savedInstanceState != null ) {
            user = savedInstanceState.getParcelable("key");
        } else {
            user = new User("HassanKachal","HassanKachal@gmail.com");
        }

        binding.setUser(user);

        binding.textView2.addTextChangedListener(this);

    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("key", Objects.requireNonNull(binding.textView2.getText()).toString());
//    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("key", (Parcelable) user);
    }

    @Override
    public void afterTextChanged(Editable s) {
        str = s.toString();
        user.setEmail(str);
    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
}
