package ru.sbrf.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.header.Header;
import ru.sbrf.util.Util;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Callable;
import org.apache.kafka.common.errors.InterruptException;

public class MessageReceiver implements Callable<String> {
    private final Consumer<Long, String> consumer;
    private final Properties fileProps;
    private final String[] topicList;

    public MessageReceiver(final String configPath) {
        this.consumer = new ConsumerCreator(configPath).createConsumer();
        this.fileProps = Util.getProperties(configPath);
        this.topicList = fileProps.getProperty("topic.name").split(",");
    }

    @Override
    public String call() throws InterruptException {
        System.out.println("Kafka consumer started successfully ");
        System.out.println("=================================================================================");
        String receivedMessage = null;
        try (Consumer<Long, String> consumer = this.consumer) {
            consumer.subscribe(Arrays.asList(topicList));
            long endTime = System.currentTimeMillis() + Long.parseLong(fileProps.getProperty("KAFKA_CONSUMER_WAIT_TIMEOUT"));

            while (!Thread.currentThread().isInterrupted()) {
                ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                System.out.println("Consumer records received: " + consumerRecords.count());
                for (ConsumerRecord<Long, String> record : consumerRecords) {
                    receivedMessage = record.value();
                    Header[] headers = record.headers().toArray();
                    for (Header header: headers) {
                        if (fileProps.getProperty("ID").equals(new String(header.value()))) {
                            consumer.commitAsync();
                            System.out.println("Kafka consumer received message: " + receivedMessage);
                            System.out.println("=================================================================================");
                            return receivedMessage;
                        }
                    }
                }
                consumer.commitAsync();
                if (endTime < System.currentTimeMillis()) {
                    System.out.println("Kafka record ID not found. Consumer will be interrupted by timeout.");
                    System.out.println("TEST FAILED...");
                    System.out.println("=================================================================================");
                    consumer.close();
                    Thread.currentThread().interrupt();
                }
            }
        }
        return receivedMessage;
    }
}
