package com.example.airportmicroservice.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@ToString
public class DeleteAirPortEvent {

    public String airportId;
}
