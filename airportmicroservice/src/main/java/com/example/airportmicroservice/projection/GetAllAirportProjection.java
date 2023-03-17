package com.example.airportmicroservice.projection;

import com.example.airportmicroservice.dto.AirportDto;
import com.example.airportmicroservice.entity.Airport;
import com.example.airportmicroservice.query.GetAirportByIdQuery;
import com.example.airportmicroservice.query.GetAllAirportQuery;
import com.example.airportmicroservice.repo.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GetAllAirportProjection {

    @Autowired
    AirportRepository airportRepository;

    @QueryHandler
    public List<AirportDto> getAllAirportQueryHandler(GetAllAirportQuery getAllAirportQuery) {
        log.info("Inside getAllAirportQueryHandler of GetAllAirportProjection");
        List<Airport> dataFromRepo = airportRepository.findAll();
        log.info("Returning response from getAllAirportQueryHandler");
        return dataFromRepo.stream().map(airport -> AirportDto.builder()
                .name(airport.getName()).location(airport.getLocation()).airportId(airport.getAirportId()).build()).collect(Collectors.toList());
    }

    @QueryHandler
    public AirportDto getAirportByIdQueryHandler(GetAirportByIdQuery getAirportByIdQuery) {
        log.info("Inside getAirportByIdQueryHandler of GetAllAirportProjection");
        Airport airport = airportRepository.findById(getAirportByIdQuery.getAirportId()).get();
        AirportDto airportDto = AirportDto.builder().airportId(airport.getAirportId()).name(airport.getName()).location(airport.getLocation()).build();
        return airportDto;
    }
}
