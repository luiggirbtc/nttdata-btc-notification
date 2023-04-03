package com.nttdata.btc.notification.app.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties
public class CustomerCreatedEvent extends Event<CustomerResponse> implements Serializable {
}