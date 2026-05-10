package com.amirbahadoramiri.androidlearning.views.videoview;

public interface TimeConvertor {

    default void hour(String h){}
    default void hour(int h){}

    default void minute(String m){}
    default void minute(int m){}

    default void second(String s){}
    default void second(int s){}

    default void timer(String h,String m,String s) {}
    default void timer(int h,int m,int s) {}

}
