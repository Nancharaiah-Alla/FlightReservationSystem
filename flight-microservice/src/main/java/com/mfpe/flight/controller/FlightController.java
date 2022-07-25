package com.mfpe.flight.controller;

import java.time.LocalDate;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.flight.entity.Flight;
import com.mfpe.flight.exception.FlightException;
import com.mfpe.flight.service.FlightService;

@CrossOrigin()
@EnableTransactionManagement
@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@PostMapping("/add")
	public String addFlight(@RequestBody Flight flight, HttpSession session) {
		try {
			int id = flightService.addFlight(flight);
			return "Flight added with flight number : " + id;

		} catch (FlightException e) {
			e.printStackTrace();
			return "" + e.getMessage();
		}

	}

	@GetMapping("/fetchall")
	public Collection<Flight> searchFlights() {
		return flightService.fetchAll();
	}

	@GetMapping("/fetch")
	public ResponseEntity<?> searchFlight(@RequestParam String source, @RequestParam String destination,
			@RequestParam String date) {
		try {

			LocalDate dt = LocalDate.parse(date);
			Collection<Flight> flights = flightService.fetchFlightsOnCondition(source, destination, dt);
			return new ResponseEntity<Collection<Flight>>(flights, HttpStatus.OK);

		} catch (FlightException e) {

			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/remove/{fid}")
	public String removeFlight(@PathVariable int fid, HttpSession session) {

		flightService.removeFlight(fid);
		return "flight removed with id : " + fid;
	}

	@PutMapping("/update")
	public String updateFlight(@RequestBody Flight flight, HttpSession session) {
		try {

			int id = flightService.updateFlight(flight);
			return "Flight updated with id " + id;

		} catch (FlightException e) {

			e.printStackTrace();
			return "" + e.getMessage();
		}
	}
}
