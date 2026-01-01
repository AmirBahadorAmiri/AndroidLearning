package com.amirbahadoramiri.androidlearning.views.retrofit.jackson;

import com.amirbahadoramiri.androidlearning.models.Character;

import java.util.List;

public interface RetrofitJacksonContract {

    interface View {

        void showItems(List<Character> characterList);

    }

    interface Presenter {

        void onAttach(View view);

        void onDetach();

        void loadItem();

    }

}
