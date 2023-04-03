package com.nttdata.btc.notification.app.controller;

import com.nttdata.btc.notification.app.model.entity.Notification;
import com.nttdata.btc.notification.app.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Class NotificationController.
 *
 * @author lrs
 */
@Slf4j
@Tag(name = "Notification", description = "Service notification")
@RestController
@RequestMapping("/api/v1")
public class NotificationController {
    /**
     * Inject dependency NotificationService.
     */
    @Autowired
    private NotificationService service;

    /**
     * Service list all notifications.
     *
     * @return {@link Notification}
     */
    @Operation(summary = "Get all notifications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Notification.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping(value = "/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Notification> findAll() {
        log.info("Start findAll Notifications.");
        return service.findAll().doOnNext(customer -> log.info(customer.toString()));
    }
}