package com.mfpe.flight.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.mfpe.flight.entity.Flight;
import com.mfpe.flight.exception.FlightException;

@Service
public interface FlightService {
	
	
	Collection<Flight> fetchAll();

	Flight fetchFlight(String source, String destination, LocalDate scheduleDate) throws FlightException;

	Collection<Flight> fetchFlightsOnCondition(String source, String destination, LocalDate scheduleDate) throws FlightException;
	
	int addFlight(Flight flight) throws FlightException;
	
	int updateFlight(Flight flight) throws FlightException;

	void removeFlight(int flightNumber); 

}
