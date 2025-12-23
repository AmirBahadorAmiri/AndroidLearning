package com.amirbahadoramiri.androidlearning.views.activities.mvvm;

public class MvvmUserTestClass {
    String name,email;

    public MvvmUserTestClass() {}

    public MvvmUserTestClass(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
