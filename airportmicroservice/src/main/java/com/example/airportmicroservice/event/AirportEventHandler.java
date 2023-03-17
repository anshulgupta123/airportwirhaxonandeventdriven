package com.example.airportmicroservice.event;

import com.example.airportmicroservice.command.DeleteAirportCommand;
import com.example.airportmicroservice.entity.Airport;
import com.example.airportmicroservice.repo.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AirportEventHandler {


    @Autowired
    AirportRepository airportRepository;

    @EventHandler
    public void eventHandlerAirport(AirportEvent airportEvent) {
        log.info("Inside eventHandlerAirport of AirportEventHandler");
        Airport airport = new Airport();
        BeanUtils.copyProperties(airportEvent, airport);
        airportRepository.save(airport);
        log.info("Data saved in airport table");
    }

    @EventHandler
    public void updateAirportHandler(UpdateAirportEvent updateAirportEvent) {
        log.info("Inside updateAirportEvent of AirportEventHandler");
        Airport airport = airportRepository.findById(updateAirportEvent.getAirportId()).get();
        airport.setAirportId(updateAirportEvent.getAirportId());
        airport.setName(updateAirportEvent.getName());
        airport.setLocation(updateAirportEvent.getLocation());
        airportRepository.save(airport);
        log.info("Airport Updated Succesfully");
    }


    @EventHandler
    public void deleteAirportHandler(DeleteAirPortEvent deleteAirPortEvent) {
        log.info("Inside deleteAirportHandler of AirportEventHandler");
        airportRepository.deleteById(deleteAirPortEvent.getAirportId());
        log.info("Airport Deleted Succesfully");
    }

    @ExceptionHandler
    public void exceptionHanlder(Exception e) throws Exception {
        log.info("Exception occured");
        throw new Exception(e.getMessage());
    }
}
