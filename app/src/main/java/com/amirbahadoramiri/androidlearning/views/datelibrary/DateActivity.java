package com.amirbahadoramiri.androidlearning.views.datelibrary;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primecalendar.persian.PersianCalendar;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

public class DateActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PrimeCalendar calendar = new PersianCalendar();
        String str = calendar.getYear()+"/"+calendar.getMonth()+"/"+calendar.getDayOfMonth();
        Logger.logd(str);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }
}
