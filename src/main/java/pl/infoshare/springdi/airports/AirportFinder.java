package pl.infoshare.springdi.airports;

import pl.infoshare.springdi.airports.model.Airport;

import java.util.Optional;

public interface AirportFinder {
    Optional<Airport> find(String iata);
}
