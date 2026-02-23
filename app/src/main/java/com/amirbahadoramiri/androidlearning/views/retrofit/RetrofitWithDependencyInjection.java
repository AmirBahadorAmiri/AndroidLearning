package com.amirbahadoramiri.androidlearning.views.retrofit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class RetrofitWithDependencyInjection {
    @Provides
    public RetrofitClient.RetrofitInterfaces get() {
        return new Retrofit.Builder()
                .baseUrl("https://google.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(RetrofitClient.RetrofitInterfaces.class);
    }

}
