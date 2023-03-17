package com.example.airportmicroservice.event;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AirportEvent {

    private String airportId;
    private String name;
    private String location;
}
