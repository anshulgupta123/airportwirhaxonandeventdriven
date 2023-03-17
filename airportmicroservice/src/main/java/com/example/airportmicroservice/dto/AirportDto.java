package com.example.airportmicroservice.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
@Builder
public class AirportDto {
    private String airportId;
    private String name;
    private String location;

}
