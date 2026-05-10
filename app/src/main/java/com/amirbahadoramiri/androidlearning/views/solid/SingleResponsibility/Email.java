package com.amirbahadoramiri.androidlearning.views.solid.SingleResponsibility;

public class Email {

    EmailServices emailServices;

    public Email(EmailServices emailServices) {
        this.emailServices = emailServices;
    }

    public void sendEmail(User user, String text) {
        String name = user.getName();
        String email = user.getEmail();
        emailServices.sendEmail(email,text);
    }
}
