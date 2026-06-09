package com.amirbahadoramiri.androidlearning.views.socketprogramming.withcode;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityWebSocketBinding;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class SocketProgramming extends BaseActivity {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    private final String SERVER_IP = "10.141.130.22";
    private final int SERVER_PORT = 9000;

    ActivityWebSocketBinding binding;
    ChatAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_web_socket);
        setViewCompat();

        adapter = new ChatAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvMain.setLayoutManager(layoutManager);
        binding.rvMain.setAdapter(adapter);

        binding.sendButton.setOnClickListener(v -> {

            String text = Objects.requireNonNull(binding.edittext.getText()).toString();

            if ( text.isEmpty() ) {
                Toast.makeText(this, "ِیه متنی چیزی بنویس", Toast.LENGTH_SHORT).show();
            } else {
                binding.edittext.setText("");
                adapter.addChat(text);
                Thread thread = new Thread(() -> connectToServer(text));
                thread.start();
            }
        });

    }

    private void connectToServer(String chat) {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            runOnUiThread(() -> Toast.makeText(this, "اتصال برقرار شد!", Toast.LENGTH_SHORT).show());

            // ارسال پیام تست
            sendMessage(chat);

            // دریافت پاسخ
            String response;
            while ((response = input.readLine()) != null) {
                String finalResponse = response;
                runOnUiThread(() -> {

                    adapter.addChat(finalResponse);
                    Log.d("Socket", finalResponse);

                });
            }

        } catch (IOException e) {
            Logger.debug(e.getMessage());
            runOnUiThread(() -> Toast.makeText(this, "خطا در اتصال: " + e.getMessage(), Toast.LENGTH_LONG).show());
        } catch (Exception e) {
            Logger.debug(e.getMessage());
        }
    }

    private void sendMessage(String message) {
        if (output != null) {
            output.println(message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            Logger.debug(e.getMessage());
        }
    }

}
