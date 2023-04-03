package com.nttdata.btc.notification.app.repository;

import com.nttdata.btc.notification.app.model.entity.Notification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Response bean CustomerRepository.
 *
 * @author lrs
 */
@Repository
public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {
}