package com.amirbahadoramiri.androidlearning.views.activities.testcompany;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.newmodels.Company;
import com.amirbahadoramiri.androidlearning.newmodels.KalaItem;
import com.amirbahadoramiri.androidlearning.newmodels.MojodiItem;
import com.amirbahadoramiri.androidlearning.newmodels.WarehouseItem;
import com.amirbahadoramiri.androidlearning.tools.database.RoomDB;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CompanyActicity extends BaseActivity {

    RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_company);
        setViewCompat();

        recyclerview = findViewById(R.id.recyclerview);

        Company company = new Company();

        List<KalaItem> kala = new ArrayList<>();
        kala.add(new KalaItem("kafsh"));
        kala.add(new KalaItem("pirahan"));
        kala.add(new KalaItem("shalvar"));
        company.setKala(kala);

        List<WarehouseItem> warehouse = new ArrayList<>();
        WarehouseItem anbar1 = new WarehouseItem("anbar1");
        List<MojodiItem> mojodiItems1 = new ArrayList<>();
        mojodiItems1.add(new MojodiItem(1, "kafsh", 10));
        mojodiItems1.add(new MojodiItem(2, "pirahan", 20));
        mojodiItems1.add(new MojodiItem(3, "shalvar", 30));
        anbar1.setMojodi(mojodiItems1);
        WarehouseItem anbar2 = new WarehouseItem("anbar2");
        List<MojodiItem> mojodiItems2 = new ArrayList<>();
        mojodiItems2.add(new MojodiItem(1, "kafsh", 10));
        mojodiItems2.add(new MojodiItem(2, "pirahan", 20));
        mojodiItems2.add(new MojodiItem(3, "shalvar", 30));
        anbar2.setMojodi(mojodiItems2);
        warehouse.add(anbar1);
        warehouse.add(anbar2);

        company.setWarehouse(warehouse);

        RoomDB.getInstance(this)
                .companyDAO()
                .getCompany()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Company>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Company company) {
                        Logger.logd(company.getWarehouse().getFirst().getName());
                        Logger.logd(company.getWarehouse().getLast().getName());
                        Logger.logd(company.getKala().getFirst().getName());
                        Logger.logd(company.getKala().getLast().getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }
}
