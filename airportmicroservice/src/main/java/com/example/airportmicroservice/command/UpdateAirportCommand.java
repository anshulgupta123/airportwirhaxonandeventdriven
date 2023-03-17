package com.example.airportmicroservice.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@ToString
@Builder
public class UpdateAirportCommand {
    @TargetAggregateIdentifier
    private String airportId;
    private String name;
    private String location;
}
