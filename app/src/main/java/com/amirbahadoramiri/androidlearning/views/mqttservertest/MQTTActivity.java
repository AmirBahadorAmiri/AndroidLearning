package com.amirbahadoramiri.androidlearning.views.mqttservertest;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MQTTActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_mqtt);
        setViewCompat();


        Mqtt5AsyncClient client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost("broker.hivemq.com")
                .serverPort(1883)
                .identifier("android-client-1")
                .buildAsync();

        client.connect();

        Logger.debug("publishWith");
        client.publishWith()
                .topic("amir/test/android")
                .payload("سلام از HiveMQ 🚀".getBytes())
                .send();

        client.subscribeWith()
                .topicFilter("amir/test/android")
                .qos(MqttQos.EXACTLY_ONCE)
                .callback(publish -> {
                    String msg = new String(publish.getPayloadAsBytes());
                    Logger.debug("subscribeWith");
                    Logger.debug(msg);
                })
                .send();




//        client.disconnect();


//        String broker = "tcp://broker.hivemq.com:1883";
//        String clientId = MqttClient.generateClientId();
//
//        MqttClient client = null;
//        try {
//            client = new MqttClient(
//                    broker,
//                    clientId,
//                    new MemoryPersistence()
//            );
//
//            client.connect();
//            client.publish("amir/test", new MqttMessage("سلام".getBytes()));
//            client.connectWithResult(new MqttConnectOptions() {
//            });
//            Logger.logd("onConnectSuccess");
//
//        } catch (Exception e) {
//            Logger.logd("onConnectFailure");
//            Logger.logd(e.getMessage());
//        }


////        ساخت MQTT Client
//        String brokerUrl = "tcp://broker.hivemq.com:1883";
//        String clientId = MqttClient.generateClientId();
//
//        MqttAndroidClient mqttClient = new MqttAndroidClient(this, brokerUrl, clientId);
//
//
//
////        اتصال به سرور MQTT
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setCleanSession(true);
//
//        try {
//            mqttClient.connect(options, null, new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    Logger.logd("Connected");
//
//                    //        ارسال متن کوتاه (Publish)
//                    String topic = "test/chat";
//                    String message = "سلام از اندروید 👋";
//
//                    try {
//                        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
//                        mqttMessage.setQos(1); // QoS 0,1,2
//
//                        mqttClient.publish(topic, mqttMessage);
//                    } catch (MqttException e) {
//                        Logger.logd("Connection failed 3");
//                    }
//
//
//
//
//
//
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    Logger.logd("Connection failed 1");
//                    Logger.logd(exception.getMessage());
//                }
//            });
//        } catch (MqttException e) {
//            Logger.logd("Connection failed 2");
//        }


    }
}
