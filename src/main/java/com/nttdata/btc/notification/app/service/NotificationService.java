package com.nttdata.btc.notification.app.service;

import com.nttdata.btc.notification.app.model.entity.Notification;
import reactor.core.publisher.Flux;

/**
 * Service NotificationService.
 *
 * @author lrs.
 */
public interface NotificationService {
    /**
     * Method findAll.
     */
    Flux<Notification> findAll();
}