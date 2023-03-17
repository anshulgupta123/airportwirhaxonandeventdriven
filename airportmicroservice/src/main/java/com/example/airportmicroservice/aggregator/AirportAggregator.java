package com.example.airportmicroservice.aggregator;

import com.example.airportmicroservice.command.AirportCommand;
import com.example.airportmicroservice.command.DeleteAirportCommand;
import com.example.airportmicroservice.command.UpdateAirportCommand;
import com.example.airportmicroservice.event.AirportEvent;
import com.example.airportmicroservice.event.DeleteAirPortEvent;
import com.example.airportmicroservice.event.UpdateAirportEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Slf4j
@Aggregate
public class AirportAggregator {

    @AggregateIdentifier
    private String airportId;
    private String name;
    private String location;

    public AirportAggregator() {
    }

    @CommandHandler
    public AirportAggregator(AirportCommand airportSaveCommand) {
        log.info("Inside command handler of airport");
        AirportEvent airportEvent = new AirportEvent();
        BeanUtils.copyProperties(airportSaveCommand, airportEvent);
        AggregateLifecycle.apply(airportEvent);
    }

    @EventSourcingHandler
    public void eventSourceHandlerAirport(AirportEvent airportEvent) {
        log.info("Inside eventSourceHandlerAirport of AirportAggregator");
        this.airportId = airportEvent.getAirportId();
        this.name = airportEvent.getName();
        this.location = airportEvent.getLocation();

    }
    @CommandHandler
    public void hanndleUpdateAirport(UpdateAirportCommand updateAirportCommand) {
        log.info("Inside hanndleUpdateAirport of UpdateAirportAggregator");
        UpdateAirportEvent updateAirportEvent = new UpdateAirportEvent();
        BeanUtils.copyProperties(updateAirportCommand, updateAirportEvent);
        AggregateLifecycle.apply(updateAirportEvent);
    }

    @EventSourcingHandler
    public void eventSourceAirportUpdate(UpdateAirportEvent updateAirportEvent) {
        log.info("Inside eventSourceAirportUpdate of UpdateAirportAggregator");
        this.airportId = updateAirportEvent.getAirportId();
        this.name = updateAirportEvent.getName();
        this.location = updateAirportEvent.getLocation();
    }

    @CommandHandler
    public void deleteAirport(DeleteAirportCommand deleteAirportCommand){
        log.info("Inside deleteAirport of UpdateAirportAggregator");
        DeleteAirPortEvent deleteAirPortEvent= new DeleteAirPortEvent();
        BeanUtils.copyProperties(deleteAirportCommand,deleteAirPortEvent);
        AggregateLifecycle.apply(deleteAirPortEvent);
    }

    @EventSourcingHandler
    public void deleteeventSourceAirport(DeleteAirPortEvent deleteAirPortEvent) {
        log.info("Inside deleteeventSourceAirport of UpdateAirportAggregator");
        this.airportId = deleteAirPortEvent.getAirportId();
    }
}
