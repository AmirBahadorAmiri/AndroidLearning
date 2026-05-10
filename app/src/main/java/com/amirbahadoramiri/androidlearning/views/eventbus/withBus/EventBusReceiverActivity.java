package com.amirbahadoramiri.androidlearning.views.eventbus.withBus;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityEventbusReceiveractivityBinding;
import com.amirbahadoramiri.androidlearning.models.User;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EventBusReceiverActivity extends BaseActivity {

    ActivityEventbusReceiveractivityBinding binding;
    DisposableObserver disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_eventbus_receiveractivity);
        setViewCompat();

        disposable = RxBus.getSubject().subscribeWith(new DisposableObserver<User>() {
            @Override
            public void onNext(User user) {
                binding.setData(user);
                Logger.logd(user.toString());
            }
            @Override public void onError(Throwable e) {}
            @Override public void onComplete() {}
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}