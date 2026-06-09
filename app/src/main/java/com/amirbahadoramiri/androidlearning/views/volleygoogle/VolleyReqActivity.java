package com.amirbahadoramiri.androidlearning.views.volleygoogle;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyReqActivity extends BaseActivity {

    private final String URL = "https://jsonplaceholder.typicode.com/users";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_volleyreq);
        setViewCompat();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Logger.debug(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Logger.debug(volleyError.getMessage());
            }
        });

        getRequestQueue().add(stringRequest);

    }

    public RequestQueue getRequestQueue() {
        if ( requestQueue == null )
            requestQueue = Volley.newRequestQueue(this);
        return requestQueue;
    }
}
