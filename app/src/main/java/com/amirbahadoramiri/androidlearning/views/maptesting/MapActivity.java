package com.amirbahadoramiri.androidlearning.views.maptesting;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.module.http.HttpRequestUtil;

public class MapActivity extends BaseActivity {

    private MapView mapView;
    private MapboxMap map = null;
    private final String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImYzZjMxMjUwYzYwODEzOTljYzhiYTE5MDNkOWE0MTM5MDJjYWZlMjAwYzdhNWNkM2RhMzAxOTBhMzAxYTU2MDM3MTZlNGY0M2E3ZDJlMjYwIn0.eyJhdWQiOiIzNjE3NyIsImp0aSI6ImYzZjMxMjUwYzYwODEzOTljYzhiYTE5MDNkOWE0MTM5MDJjYWZlMjAwYzdhNWNkM2RhMzAxOTBhMzAxYTU2MDM3MTZlNGY0M2E3ZDJlMjYwIiwiaWF0IjoxNzY3MjQ2NzM0LCJuYmYiOjE3NjcyNDY3MzQsImV4cCI6MTc2OTc1MjMzNCwic3ViIjoiIiwic2NvcGVzIjpbImJhc2ljIl19.pBZkqVnpMYXM2Srhlx_ZA2-m6GCS7k9xDaaRDaHRXK8EB6LxfN19-RAy2Fps6E59tWOhRqQNfh4TDjq_nZNKQ5MtM_EbVlTEQ7fEap8oBM3vlRDv6ie1WnMD1btiXXmjboU9bvlGtNXuhR1AHFrKBXTPdLrF9SS2_CSFvadAmwrr5jh9Tp48ZsafdrZDoXI4QUM7WbdwfSIPlc7ydTo_Q6VnhMS_vcodJdfMy0EtFvnmG0QuKtWkI-9Gjw__OhYwMDtSOIfQSvOGyivWwaiWfO4jTf_gwpK-9pFEVacD4l7sKdbFRyhVt1oU6fkZzgkBICIExoc--_n-rZh4755nqA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext());

        edgeEnabled();
        setContentView(R.layout.activity_map_testing);
        setViewCompat();


        mapView = findViewById(R.id.mapview);
        runOnUiThread(() -> init(mapView, MapirStyle.VERNA, this, apiKey));
    }

    public void init(MapView mapView, String style, Context context, String apiKey) {
        HttpRequestUtil.setOkHttpClient(new NetworkUtils(context).getOkHttpClient(apiKey));
        mapView.getMapAsync(mapboxMap -> {
            map = mapboxMap;
            map.getUiSettings().setLogoMargins(10000, 0, 0, 0);
            map.getUiSettings().setAttributionMargins(10000, 0, 0, 0);
            mapboxMap.setStyle(new Style.Builder().fromUri(style));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(35.690975, 51.433868))
                    .zoom(6.0)
                    .build();
            mapboxMap.setCameraPosition(cameraPosition);
        });
    }

}
