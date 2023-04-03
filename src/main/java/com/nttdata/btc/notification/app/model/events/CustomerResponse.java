package com.nttdata.btc.notification.app.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Response bean CustomerResponse.
 *
 * @author lrs
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonIgnoreProperties
public class CustomerResponse {
    private String type_customer;
    private String category_customer;
    private String name;
    private String surname;
    private String type_document;
    private String number_document;
    private String phone;
    private String email;
    private String id_customer;
    private Date registerDate;
    private Boolean status = false;
}