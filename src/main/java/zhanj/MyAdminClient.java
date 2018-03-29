package zhanj;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MyAdminClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.27.24.101:9092,172.27.24.146:9092,172.27.24.176:9092");
        AdminClient client = AdminClient.create(props);
        NewTopic topic = new NewTopic("new-topic", 2, (short)2);
        CreateTopicsResult result = client.createTopics(Arrays.asList(topic));
        KafkaFuture<Void> future = result.all();
        future.get();
        client.close();
    }
}
