package zhanj;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.27.24.101:9092,172.27.24.146:9092,172.27.24.176:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        org.apache.kafka.clients.producer.Producer<String, String> producer = new KafkaProducer<>(props);
        Future<RecordMetadata> future = producer.send(new ProducerRecord<>("test-topic", 0, "hello", "shabi"));
        RecordMetadata metadata = future.get();
        System.out.format("partition: %d, offset: %d, timestamp: %d, %n", metadata.partition(), metadata.offset(), metadata.timestamp());
        producer.close();
    }
}
