package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.18.135:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        properties.put("acks","1");
        properties.put("batch.size","16384");
        properties.put("linger.ms","5");
        properties.put("buffer.memory","33554432");
        properties.put("max.block.ms","3000");

        Producer<String,String> producer = new KafkaProducer<String, String>(properties);

        for(int i=0;i<100;i++){
            producer.send(new ProducerRecord<String, String>("myTopic",Integer.toBinaryString(i),Integer.toString(i)));
        }
        producer.close();
    }
}
