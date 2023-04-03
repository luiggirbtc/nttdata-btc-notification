package com.nttdata.btc.notification.app.config;

import com.nttdata.btc.notification.app.model.events.Event;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${topic.customer.bootstrap-address}")
    private String bootstrapAddress;

    @Value("${topic.customer.host}")
    private String topicHost;

    @Bean
    public ConsumerFactory<String, Event<?>> consumerFactory() {
        Map<String, String> props = new HashMap<>();
        String bootstrapServers = topicHost.concat(":".concat(bootstrapAddress));
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        String autoOffsetReset = "latest";
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.nttdata.btc.customer.app.model.events.CustomerCreatedEvent");//props.put(JsonDeserializer.TRUSTED_PACKAGES,*)
        props.put(JsonSerializer.TYPE_MAPPINGS, "com.nttdata.btc.customer.app.model.events.CustomerCreatedEvent:com.nttdata.btc.notification.app.model.events.CustomerCreatedEvent");

        final JsonDeserializer<Event<?>> jsonDeserializer = new JsonDeserializer<>();
        return new DefaultKafkaConsumerFactory(
                props,
                new StringDeserializer(),
                jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event<?>> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Event<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}