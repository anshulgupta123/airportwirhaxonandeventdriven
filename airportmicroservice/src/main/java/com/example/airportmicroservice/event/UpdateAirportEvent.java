package com.example.airportmicroservice.event;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@ToString
public class UpdateAirportEvent {

    private String airportId;
    private String name;
    private String location;
}
