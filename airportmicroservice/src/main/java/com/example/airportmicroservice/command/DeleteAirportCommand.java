package com.example.airportmicroservice.command;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
@Setter
@ToString
public class DeleteAirportCommand {

    @TargetAggregateIdentifier
    public String airportId;
}
