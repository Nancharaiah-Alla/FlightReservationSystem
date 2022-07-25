package com.mfpe.flight.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Flight {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int flightNumber;
	
	private String source;
	
	private String destination;
	
	@Column(name="travel_date")
	private LocalDate travelDate;
	
	@Column(name="arrival_time")
	private LocalTime arrivalTime;
	
	@Column(name="departure_time")
	private LocalTime departureTime;
	
	private double price;
	
	private int availableSeats;

}
