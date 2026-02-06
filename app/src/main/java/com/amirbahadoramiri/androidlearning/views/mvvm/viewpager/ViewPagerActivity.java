package com.amirbahadoramiri.androidlearning.views.mvvm.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ItemMvvmViewpagerBinding;
import com.amirbahadoramiri.androidlearning.models.Character;
import com.amirbahadoramiri.androidlearning.models.CharacterJacksonWrapper;
import com.amirbahadoramiri.androidlearning.views.retrofit.RetrofitClient;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewPagerActivity extends BaseActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_viewpager);
        setViewCompat();

        findViews();
        setupViews();

    }

    private void findViews() {

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

    }

    private void setupViews() {

        List<Character> characterList = new ArrayList<>();
        ViewPagerAdapter adapter = new ViewPagerAdapter(characterList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager,true);

        RetrofitClient.getRetrofitInterfaces()
                .listCharactersJackson("https://rickandmortyapi.com/api/character/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull CharacterJacksonWrapper characterJacksonWrapper) {
                        characterList.addAll(characterJacksonWrapper.getResults());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ViewPagerActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                    }
                });

    }

    public class ViewPagerAdapter extends PagerAdapter {

        List<Character> characterList;

        public ViewPagerAdapter(List<Character> characterList) {
            this.characterList = characterList;
        }

        @Override
        public int getCount() {
            return characterList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ItemMvvmViewpagerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.item_mvvm_viewpager, container, false);
            binding.setData(characterList.get(position));
            container.addView(binding.getRoot());
            return binding.getRoot();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return characterList.get(position).getName();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

}
