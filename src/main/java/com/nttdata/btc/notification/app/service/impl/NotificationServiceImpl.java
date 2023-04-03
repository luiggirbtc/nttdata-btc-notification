package com.nttdata.btc.notification.app.service.impl;

import com.nttdata.btc.notification.app.model.entity.Notification;
import com.nttdata.btc.notification.app.repository.NotificationRepository;
import com.nttdata.btc.notification.app.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * Service Implement NotificationService.
 *
 * @author lrs
 */
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {
    /**
     * Inject dependency {@link NotificationRepository}
     */
    @Autowired
    NotificationRepository repository;

    /**
     * This method return all notifications.
     *
     * @return {@link List<Notification>}
     */
    @Override
    public Flux<Notification> findAll() {
        return repository.findAll().filter(Notification::isStatus)
                .onErrorResume(e -> Flux.error(customException(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())));
    }

    /**
     * Method custom exception.
     *
     * @param status  {@link HttpStatus}
     * @param message {@link String}
     * @return {@link ResponseStatusException}
     */
    private ResponseStatusException customException(HttpStatus status, String message) {
        return new ResponseStatusException(status, message);
    }
}