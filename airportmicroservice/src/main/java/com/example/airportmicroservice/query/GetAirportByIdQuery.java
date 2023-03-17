package com.example.airportmicroservice.query;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetAirportByIdQuery {

    private String airportId;
}
