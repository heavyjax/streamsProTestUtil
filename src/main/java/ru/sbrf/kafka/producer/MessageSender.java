package ru.sbrf.kafka.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import ru.sbrf.util.Util;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MessageSender {

    private final Producer<Long, String> producer;
    private final Properties fileProps;

    public MessageSender(final String configPath) {
        this.producer = new ProducerCreator(configPath).createProducer();
        this.fileProps = Util.getProperties(configPath);
    }

    public void sendMessage(String topicName, String messageToSend, String messageType) throws ExecutionException, InterruptedException {

        Headers headers = new RecordHeaders();
        headers.add("EventID", fileProps.getProperty("ID").getBytes());
        headers.add("EventType", messageType.getBytes());
        headers.add("NodeIdent", "OWS.PRIM.EVNT_MSG".getBytes());

        ProducerRecord<Long, String> record = new ProducerRecord(topicName, null, null,
                Long.valueOf(fileProps.getProperty("ID")), messageToSend, headers);
        RecordMetadata metadata = producer.send(record).get();
        System.out.println("Partition: " + metadata.partition() +
                           " topic: " + metadata.topic() +
                           " offset: " + metadata.offset());
        System.out.println("Kafka record was successfully send. ID: " + fileProps.getProperty("ID"));
        System.out.println("=================================================================================");
    }
}
