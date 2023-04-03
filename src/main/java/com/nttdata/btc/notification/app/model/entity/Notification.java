package com.nttdata.btc.notification.app.model.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity Notification.
 *
 * @author lrs
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notification")
public class Notification {
    @Id
    private String idNotification;

    private String type;

    private String message;

    private Date registerDate = new Date();

    private boolean status = true;
}