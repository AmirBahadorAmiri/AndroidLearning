package com.amirbahadoramiri.androidlearning.views.socketprogramming.socketio;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityWebSocketBinding;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.amirbahadoramiri.androidlearning.views.socketprogramming.withcode.ChatAdapter;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketIoProgramming extends BaseActivity {

    private final String SERVER_URL = "http://10.141.130.177:9000";
    private Socket mSocket;

    ActivityWebSocketBinding binding;
    ChatAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_socket);
        setViewCompat();

        adapter = new ChatAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvMain.setLayoutManager(layoutManager);
        binding.rvMain.setAdapter(adapter);

        binding.sendButton.setOnClickListener(v -> {

            String text = Objects.requireNonNull(binding.edittext.getText()).toString();

            if (text.isEmpty()) {
                Toast.makeText(this, "ِیه متنی چیزی بنویس", Toast.LENGTH_SHORT).show();
            } else {
                binding.edittext.setText("");
                adapter.addChat(text);
                sendMessage(text);
            }
        });


        try {
            mSocket = IO.socket(SERVER_URL);

            // گوش دادن به رویدادها
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Logger.debug(Arrays.toString(args));
                }
            });

            mSocket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Logger.debug(Arrays.toString(args));
                }
            });

            mSocket.on("message", new Emitter.Listener() {   // نام رویداد دلخواه سرور
                @Override
                public void call(Object... args) {

                    Logger.debug(Arrays.toString(args));
//                    adapter.addChat(Arrays.toString(args));

//                    JSONObject data = (JSONObject) args[0];
//                    try {
//                        String msg = data.getString("text");
//                        runOnUiThread(() -> Toast.makeText(SocketIoProgramming.this, "پیام: " + msg, Toast.LENGTH_LONG).show());
//                    } catch (JSONException e) {
//                        Logger.debug(e.getMessage());
//                    }
                }
            });

            mSocket.connect();

        } catch (URISyntaxException e) {
            Logger.debug(e.getMessage());
        }

    }

    private void sendMessage(String message) {
        if (mSocket != null && mSocket.connected()) {
            mSocket.emit("message", message);
//            JSONObject obj = new JSONObject();
//            try {
//                obj.put("text", message);
//                obj.put("username", "کاربر اندروید");
//                mSocket.emit("message", obj);   // نام رویداد سرور
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSocket != null) {
            mSocket.disconnect();
        }
    }

}
