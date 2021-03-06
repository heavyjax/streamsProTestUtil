package ru.sbrf.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import ru.sbrf.util.Util;
import java.util.Properties;

public class ProducerCreator {

    private final Properties fileProps;

    public ProducerCreator(final String configPath) {
        this.fileProps = Util.getProperties(configPath);
    }

    public Producer<Long, String> createProducer(){
        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, fileProps.getProperty("bootstrap.servers"));
        kafkaProps.put(ProducerConfig.CLIENT_ID_CONFIG, fileProps.getProperty("client.id"));
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(kafkaProps);
    }
}
