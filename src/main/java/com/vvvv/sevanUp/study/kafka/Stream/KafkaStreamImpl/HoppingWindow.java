package com.vvvv.sevanUp.study.kafka.Stream.KafkaStreamImpl;

import com.alibaba.fastjson.JSON;
import com.vvvv.sevanUp.study.kafka.Stream.KafkaStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Stream - 跳跃时间窗口
 */
@Component
@Slf4j
public class HoppingWindow extends KafkaStream {
//    @CustomCut(value = 1, describe = "stream HoppingWindow")
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        startStream("HoppingWindow");
    }

    @Override
    protected StreamsBuilder buildStream() {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> source = builder.stream("topic-hopping");
        source.map((k, v) -> {
                    String name = JSON.parseObject(v).getString("name");
                    return new KeyValue<>(name, v);
                })
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofSeconds(5L)))
                .count()
                .toStream()
                .foreach((k, v) -> log.info("hopping k={},v={}", getReadableWindowed(k), v));
        return builder;
    }
}