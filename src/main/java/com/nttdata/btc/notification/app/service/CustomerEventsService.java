package com.nttdata.btc.notification.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nttdata.btc.notification.app.model.entity.Notification;
import com.nttdata.btc.notification.app.model.events.CustomerCreatedEvent;
import com.nttdata.btc.notification.app.model.events.Event;
import com.nttdata.btc.notification.app.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Class CustomerEventsService kafka listener.
 *
 * @author lrs
 */
@Slf4j
@Component
public class CustomerEventsService {

    /**
     * Inject dependency {@link NotificationRepository}
     */
    @Autowired
    NotificationRepository repository;

    @KafkaListener(
            topics = "${topic.customer.name:customers}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1")
    public void consumer(Event<?> event) throws JsonProcessingException {
        if (event.getClass().isAssignableFrom(CustomerCreatedEvent.class)) {
            CustomerCreatedEvent customerCreatedEvent = (CustomerCreatedEvent) event;
            log.info("Received Customer created event .... with Id={}, data={}",
                    customerCreatedEvent.getId(),
                    customerCreatedEvent.getData().toString());

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = (null != customerCreatedEvent.getData()) ? ow.writeValueAsString(customerCreatedEvent.getData()) : "";

            Notification notification = new Notification();
            notification.setType(customerCreatedEvent.getType().toString());
            notification.setMessage(json);
            repository.save(notification).subscribe();
        }
    }
}