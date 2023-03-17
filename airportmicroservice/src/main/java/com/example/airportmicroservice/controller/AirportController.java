package com.example.airportmicroservice.controller;

import com.example.airportmicroservice.Utility.Constants;
import com.example.airportmicroservice.Utility.UrlConstants;
import com.example.airportmicroservice.command.AirportCommand;
import com.example.airportmicroservice.command.DeleteAirportCommand;
import com.example.airportmicroservice.command.UpdateAirportCommand;
import com.example.airportmicroservice.dto.AirportDto;
import com.example.airportmicroservice.query.GetAirportByIdQuery;
import com.example.airportmicroservice.query.GetAllAirportQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class AirportController {

    @Autowired
    CommandGateway commandGateway;

    @Autowired
    QueryGateway queryGateway;

    @Autowired
    Environment environment;

    @PostMapping(UrlConstants.ADD_AIRPORT)
    public ResponseEntity<Object> saveAirport(@RequestBody AirportDto airportDto) {
        log.info("Request for saveAirport of AirportController :{}", airportDto);
        String airportId = UUID.randomUUID().toString();
        AirportCommand airportSaveCommand = AirportCommand.builder().airportId(airportId).location(airportDto.getLocation()).name(airportDto.getName()).build();
        commandGateway.sendAndWait(airportSaveCommand);
        return new ResponseEntity<>(environment.getProperty(Constants.AIRPORT_CREATED), HttpStatus.OK);
    }

    @GetMapping(UrlConstants.GET_ALL_AIRPORT)
    public ResponseEntity<Object> getAllAirport() {
        log.info("Request for getAllAirport of AirportController");
        GetAllAirportQuery getAllAirportQuery = new GetAllAirportQuery();
        List<AirportDto> listOfAirportDto = queryGateway.query(getAllAirportQuery, ResponseTypes.multipleInstancesOf(AirportDto.class)).join();
        return new ResponseEntity<>(listOfAirportDto, HttpStatus.OK);
    }

    @GetMapping(UrlConstants.GET_AIRPORT_BY_ID)
    public ResponseEntity<Object> getAirportById(@RequestParam String airportId) {
        log.info("Inside getAirportById of AirportController");
        GetAirportByIdQuery getAirportByIdQuery = new GetAirportByIdQuery(airportId);
        AirportDto airportDto = queryGateway.query(getAirportByIdQuery, ResponseTypes.instanceOf(AirportDto.class)).join();
        return new ResponseEntity<>(airportDto, HttpStatus.OK);
    }

    @PutMapping(UrlConstants.UPDATE_AIRPORT)
    public ResponseEntity<Object> updateAirport(@RequestBody AirportDto airportDto) {
        log.info("Inside updateAirport of AirportController");
        UpdateAirportCommand updateAirportCommand = UpdateAirportCommand.builder().
                airportId(airportDto.getAirportId()).location(airportDto.getLocation()).name(airportDto.getName()).build();
        commandGateway.sendAndWait(updateAirportCommand);
        return new ResponseEntity<>(environment.getProperty(Constants.AIRPORT_UPDATED), HttpStatus.OK);
    }

    @DeleteMapping(UrlConstants.DELETE_AIRPORT)
    public ResponseEntity<Object> deleteAirport(@RequestParam String airportId) {
        log.info("Inside updateAirport of AirportController");
        DeleteAirportCommand deleteAirportCommand = DeleteAirportCommand.builder().airportId(airportId).build();
        commandGateway.sendAndWait(deleteAirportCommand);
        return new ResponseEntity<>(environment.getProperty(Constants.AIRPORT_DELETED), HttpStatus.OK);

    }
}




