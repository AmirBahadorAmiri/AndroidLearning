package com.amirbahadoramiri.androidlearning.views.eventbus.withBus;

import com.amirbahadoramiri.androidlearning.models.User;

import java.util.UUID;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

public final class RxBus {

    private static User data;
    private static final BehaviorSubject<User> behaviorSubject = BehaviorSubject.create();

    public static BehaviorSubject<User> getSubject() {
        return behaviorSubject;
    }

    public static User user() {
        if (data == null) {
            data = new User(UUID.randomUUID().toString(), "random@gmail.com");
        }
        return data;
    }

}