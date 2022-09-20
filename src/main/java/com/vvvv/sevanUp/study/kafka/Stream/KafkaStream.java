package com.vvvv.sevanUp.study.kafka.Stream;

import lombok.Data;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Windowed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * kfk consumer stream
 */
@Component
public abstract class KafkaStream implements ApplicationRunner {

    @Value("${spring.kafka.bootstrap-servers}")
    protected String kfkUrls;

    Windowed<String> getReadableWindowed(Windowed<String> key) {
        return new Windowed<String>(key.key(), key.window()) {
            @Override
            public String toString() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
                return key() + "@" + "[" + sdf.format(window().start()) + "/" + sdf.format(window().end()) + "]";
            }
        };
    }

    abstract StreamsBuilder buildStream();


    void startStream(String idConfig) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "vvvv-" + idConfig);
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kfkUrls);
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "50");
        new KafkaStreams(buildStream().build(), config).start();
    }
}
