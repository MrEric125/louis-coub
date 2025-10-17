package com.louis.kafka;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.NoOffsetForPartitionException;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.clients.consumer.OffsetOutOfRangeException;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.RebalanceInProgressException;
import org.apache.kafka.common.errors.RetriableException;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import static java.time.Duration.ofMillis;
import static java.util.Collections.singleton;

public class KafkaConsumerTest implements ConsumerRebalanceListener, OffsetCommitCallback {
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "my-group";
    private static final long POLL_TIMEOUT_MS = 1_000L;
    private static final String TOPIC_NAME = "topic10";
    private static final long NUM_MESSAGES = 100;
    private static final long PROCESSING_DELAY_MS = 10L;

    private KafkaConsumer<Long, byte[]> kafkaConsumer;
    protected AtomicLong messageCount = new AtomicLong(0);
    private Map<TopicPartition, OffsetAndMetadata> pendingOffsets = new HashMap<>();

    public KafkaConsumerTest() {
        kafkaConsumer = createKafkaConsumer();
    }

    public static void main(String[] args) {
        KafkaConsumerTest kafkaConsumerTest = new KafkaConsumerTest();
        while (true) {
            kafkaConsumerTest.run();
        }
    }

    public void run() {
        System.out.println("Running consumer");
        kafkaConsumer.subscribe(singleton(TOPIC_NAME), this);
        System.out.printf("Subscribed to %s%n", TOPIC_NAME);
        while (messageCount.get() < NUM_MESSAGES) {
            try {
                ConsumerRecords<Long, byte[]> records = kafkaConsumer.poll(ofMillis(POLL_TIMEOUT_MS));
                if (!records.isEmpty()) {
                    for (ConsumerRecord<Long, byte[]> record : records) {
                        System.out.printf("Record fetched from %s-%d with offset %d%n",
                                record.topic(), record.partition(), record.offset());


                        pendingOffsets.put(new TopicPartition(record.topic(), record.partition()),
                                new OffsetAndMetadata(record.offset() + 1, null));
                        if (messageCount.incrementAndGet() == NUM_MESSAGES) {
                            break;
                        }
                    }
                    kafkaConsumer.commitAsync(pendingOffsets, this);
                    pendingOffsets.clear();
                }
            } catch (OffsetOutOfRangeException | NoOffsetForPartitionException e) {
                System.out.println("Invalid or no offset found, using latest");
                kafkaConsumer.seekToEnd(e.partitions());
                kafkaConsumer.commitSync();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                if (!retriable(e)) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
        sleep(PROCESSING_DELAY_MS);
    }

    private KafkaConsumer<Long, byte[]> createKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "client-" + UUID.randomUUID());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new KafkaConsumer<>(props);
    }

    private void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean retriable(Exception e) {
        if (e == null) {
            return false;
        } else if (e instanceof IllegalArgumentException
                || e instanceof UnsupportedOperationException
                || !(e instanceof RebalanceInProgressException)
                || !(e instanceof RetriableException)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.printf("Assigned partitions: %s%n", partitions);
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.printf("Revoked partitions: %s%n", partitions);
        kafkaConsumer.commitSync(pendingOffsets);
        pendingOffsets.clear();
    }

    @Override
    public void onPartitionsLost(Collection<TopicPartition> partitions) {
        System.out.printf("Lost partitions: {}", partitions);
    }

    @Override
    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
        if (e != null) {
            System.err.println("Failed to commit offsets");
            if (!retriable(e)) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}