package com.example.airportmicroservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name="airport")
public class Airport {

    @Id
    private String airportId;
    private String name;
    private String location;
}
