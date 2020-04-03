package ru.sbrf.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import ru.sbrf.util.Util;

import java.util.Properties;

public class ConsumerCreator {

    private final Properties fileProps;

    public ConsumerCreator() {
        this.fileProps =
                Util.getProperties("/Users/heavyjax/GoogleDrive/Dev/StreamsProTestUtil/config.properties");
    }

    public Consumer<Long, String> createConsumer() {
        Properties kafkaProps = new Properties();
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, fileProps.getProperty("bootstrap.servers"));
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, fileProps.getProperty("group.id"));
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        if (Boolean.getBoolean(fileProps.getProperty("ssl.enabled"))) {
            kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, fileProps.getProperty("bootstrap.servers"));
            kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, fileProps.getProperty("group.id"));
            kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
            kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        }

        return new KafkaConsumer<>(kafkaProps);
    }
}
