package com.amirbahadoramiri.androidlearning.views.activities.retrofit;

import com.amirbahadoramiri.androidlearning.models.Character;
import com.amirbahadoramiri.androidlearning.models.CharacterGsonWrapper;
import com.amirbahadoramiri.androidlearning.models.CharacterJacksonWrapper;
import com.amirbahadoramiri.androidlearning.models.CharacterMoshiWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static RetrofitInterfaces retrofitInterfaces;

    public static RetrofitInterfaces getRetrofitInterfaces() {

        if (retrofit == null) {

            // only for jackson converter factory
            ObjectMapper objectMapper = new ObjectMapper();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://google.com")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addConverterFactory(MoshiConverterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

        if (retrofitInterfaces == null)
            retrofitInterfaces = retrofit.create(RetrofitInterfaces.class);
        return retrofitInterfaces;
    }

    public interface RetrofitInterfaces {
        @GET
        Single<CharacterGsonWrapper> listCharacters(@Url String url);

        @GET
        Single<CharacterMoshiWrapper> listCharactersMoshi(@Url String url);

        @GET
        Single<CharacterJacksonWrapper> listCharactersJackson(@Url String url);

    }

}
