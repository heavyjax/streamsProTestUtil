package ru.sbrf.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import ru.sbrf.util.Util;

import java.util.Properties;

public class ConsumerCreator {

    private final Properties fileProps;

    public ConsumerCreator(final String configPath) {
        this.fileProps = Util.getProperties(configPath);
    }

    public Consumer<Long, String> createConsumer() {
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("bootstrap.servers", fileProps.getProperty("bootstrap.servers"));
        kafkaProps.setProperty("group.id", fileProps.getProperty("group.id"));
        kafkaProps.setProperty("key.deserializer", fileProps.getProperty("key.deserializer"));
        kafkaProps.setProperty("value.deserializer", fileProps.getProperty("value.deserializer"));
        kafkaProps.setProperty("enable.auto.commit", fileProps.getProperty("enable.auto.commit"));
        kafkaProps.setProperty("topic.name", fileProps.getProperty("topic.name"));
        if (Boolean.getBoolean(fileProps.getProperty("ssl.enabled"))) {
            kafkaProps.setProperty("security.protocol", fileProps.getProperty("security.protocol"));
            kafkaProps.setProperty("ssl.truststore.location", fileProps.getProperty("ssl.truststore.location"));
            kafkaProps.setProperty("ssl.truststore.password", fileProps.getProperty("ssl.truststore.password"));
            kafkaProps.setProperty("ssl.keystore.location", fileProps.getProperty("ssl.keystore.location"));
            kafkaProps.setProperty("ssl.keystore.password", fileProps.getProperty("ssl.keystore.password"));
            kafkaProps.setProperty("ssl.key.password", fileProps.getProperty("ssl.key.password"));
        }
        return new KafkaConsumer<>(kafkaProps);
    }
}
