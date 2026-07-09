package com.amirbahadoramiri.androidlearning.views.retrofit.websource;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.tools.rejex.RegexUtils;
import com.amirbahadoramiri.androidlearning.views.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WebSourceActivity extends BaseActivity {

    String iranjib_url = "https://www.iranjib.ir/showgroup/23/realtime_price/";
    String tasnim_url = "https://tasnimnews.ir/common/CurrencyTable";

    String regex_ons_tala = "<td id=\"f_83_63_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_18 = "<td id=\"f_85_63_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_24 = "<td id=\"f_127_63_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";

    String regex_tarh_jadid = "<td id=\"f_87_63_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_tarh_qadim = "<td id=\"f_88_63_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_nim_seke = "<td id=\"f_89_63_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_rob_seke = "<td id=\"f_90_63_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_yek_grami = "<td id=\"f_92_63_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";

    String regex_usdt = "<td id=\"f_19054_127_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_bitcoin = "<td id=\"f_8277_127_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_dollar_havale = "<td id=\"f_8652_68_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_euro_havale = "<td id=\"f_8653_68_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";
    String regex_derham_havale = "<td id=\"f_17624_68_pr\"><span class=\"lastprice\">(\\d{1,3}(?:,\\d{3})*)(?:\\.\\d+)?</span></td>";

    AppCompatTextView ons_tala,tala18,tala24;
    AppCompatTextView tarh_jadid,tarh_qadim,nimseke,robseke,yekgrami;
    AppCompatTextView usdt,bitcoin,dollar_havale,euro_havale,derham_havale;
    AppCompatTextView dollar,euro,pound,derham,lir,yuan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_web_sources);
        setViewCompat();

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_MEDIA_VIDEO,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

        ons_tala = findViewById(R.id.ons_tala);
        tala18 = findViewById(R.id.tala18);
        tala24 = findViewById(R.id.tala24);

        tarh_jadid = findViewById(R.id.tarh_jadid);
        tarh_qadim = findViewById(R.id.tarh_qadim);
        nimseke = findViewById(R.id.nimseke);
        robseke = findViewById(R.id.robseke);
        yekgrami = findViewById(R.id.yekgrami);

        usdt = findViewById(R.id.usdt);
        bitcoin = findViewById(R.id.bitcoin);
        dollar_havale = findViewById(R.id.dollar_havale);
        euro_havale = findViewById(R.id.euro_havale);
        derham_havale = findViewById(R.id.derham_havale);

        dollar = findViewById(R.id.dollar);
        euro = findViewById(R.id.euro);
        pound = findViewById(R.id.pound);
        derham = findViewById(R.id.derham);
        lir = findViewById(R.id.lir);
        yuan = findViewById(R.id.yuan);

        reload();
        Observable.interval(60, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override public void onSubscribe(@NonNull Disposable d) {}
                    @Override public void onNext(Long aLong) {
                        reload();
                    }
                    @Override public void onError(@NonNull Throwable e) {}
                    @Override public void onComplete() {}
                });

    }

    public void reload() {

        RetrofitClient.getRetrofitInterfaces().get(iranjib_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<>() {
                    @Override public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(String text) {
                        //                        saveFiles(text,"data.txt")
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new Observer<Boolean>() {
//                                    @Override public void onSubscribe(@NonNull Disposable d) {}
//                                    @Override public void onNext(Boolean aBoolean) {}
//                                    @Override public void onError(@NonNull Throwable e) {}
//                                    @Override public void onComplete() {}
//                                });

                        RegexUtils.iterator(regex_ons_tala, text, it -> ons_tala.setText(it),1);
                        RegexUtils.iterator(regex_18, text, it -> tala18.setText(it),1);
                        RegexUtils.iterator(regex_24, text, it -> tala24.setText(it),1);

                        RegexUtils.iterator(regex_tarh_jadid, text, it -> tarh_jadid.setText(it),1);
                        RegexUtils.iterator(regex_tarh_qadim, text, it -> tarh_qadim.setText(it),1);
                        RegexUtils.iterator(regex_nim_seke, text, it -> nimseke.setText(it),1);
                        RegexUtils.iterator(regex_rob_seke, text, it -> robseke.setText(it),1);
                        RegexUtils.iterator(regex_yek_grami, text, it -> yekgrami.setText(it),1);

                        RegexUtils.iterator(regex_usdt, text, it -> usdt.setText(it),1);
                        RegexUtils.iterator(regex_bitcoin, text, it -> bitcoin.setText(it),1);
                        RegexUtils.iterator(regex_dollar_havale, text, it -> dollar_havale.setText(it),1);
                        RegexUtils.iterator(regex_euro_havale, text, it -> euro_havale.setText(it),1);
                        RegexUtils.iterator(regex_derham_havale, text, it -> derham_havale.setText(it),1);

                    }
                    @Override public void onError(@NonNull Throwable e) {}
                });

        RetrofitClient.getRetrofitInterfaces().post(tasnim_url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<>() {
                    @Override public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onSuccess(String s) {

                        String text = s;
                        text = text.replace("\\\"", "\"");
                        text = text.substring(1,(text.length()-1));

                        try {
                            JSONObject object = new JSONObject(text);
                            JSONArray array = object.getJSONArray("currency");

                            String dollar_present = array.getJSONObject(160).getString("p");
                            String dollar_high = array.getJSONObject(160).getString("h");
                            String dollar_low = array.getJSONObject(160).getString("l");
                            String dollar_dt = array.getJSONObject(160).getString("dt");
                            dollar.setText(dollar_present);

                            String euro_present = array.getJSONObject(151).getString("p");
                            String euro_high = array.getJSONObject(151).getString("h");
                            String euro_low = array.getJSONObject(151).getString("l");
                            String euro_dt = array.getJSONObject(151).getString("dt");
                            euro.setText(euro_present);

                            String pound_present = array.getJSONObject(148).getString("p");
                            String pound_high = array.getJSONObject(148).getString("h");
                            String pound_low = array.getJSONObject(148).getString("l");
                            String pound_dt = array.getJSONObject(148).getString("dt");
                            pound.setText(pound_present);

                            String derham_uae_present = array.getJSONObject(202).getString("p");
                            String derham_uae_high = array.getJSONObject(202).getString("h");
                            String derham_uae_low = array.getJSONObject(202).getString("l");
                            String derham_uae_dt = array.getJSONObject(202).getString("dt");
                            derham.setText(derham_uae_present);

                            String lir_turkey_present = array.getJSONObject(64).getString("p");
                            String lir_turkey_high = array.getJSONObject(64).getString("h");
                            String lir_turkey_low = array.getJSONObject(64).getString("l");
                            String lir_turkey_dt = array.getJSONObject(64).getString("dt");
                            lir.setText(lir_turkey_present);

                            String yuan_present = array.getJSONObject(174).getString("p");
                            String yuan_high = array.getJSONObject(174).getString("h");
                            String yuan_low = array.getJSONObject(174).getString("l");
                            String yuan_dt = array.getJSONObject(174).getString("dt");
                            yuan.setText(yuan_present);

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

//                        saveFiles(text,"data.txt")
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new Observer<Boolean>() {
//                                    @Override public void onSubscribe(@NonNull Disposable d) {}
//                                    @Override public void onNext(Boolean aBoolean) {}
//                                    @Override public void onError(@NonNull Throwable e) {}
//                                    @Override public void onComplete() {}
//                                });

//                        Logger.any(text);

                    }
                    @Override public void onError(@NonNull Throwable e) {}
                });

    }

    public Observable<Boolean> saveFiles(String data, String fileName) {
        return Observable.fromCallable(() -> saveFile(data, fileName))
                .subscribeOn(Schedulers.io());
    }

    public boolean saveFile(String data, String fileName) {
        File musicDir = new File(getExternalFilesDir(null), "txt");
        if (!musicDir.exists()) {
            musicDir.mkdirs();
        }
        File file = new File(musicDir, fileName);
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
