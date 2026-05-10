package com.amirbahadoramiri.androidlearning.views.mvvm.livedata.teststring;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;

public class LiveDataTestStringActivity extends BaseActivity {

    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_live_data_test_string);
        setViewCompat();

        LiveDataTestStringViewModel stringViewModel = new ViewModelProvider(this).get(LiveDataTestStringViewModel.class);
        stringViewModel.getStringMutableLiveData().observe(this, s -> data = s);

        Button btn = findViewById(R.id.btn);
        EditText edittext = findViewById(R.id.edittext);

        btn.setOnClickListener(v -> {
            if (data.isEmpty()) {
                stringViewModel.getStringMutableLiveData().setValue(edittext.getText().toString());
                Toast.makeText(this, "isEmpty: " + data, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, data, Toast.LENGTH_LONG).show();
            }
        });

    }
}