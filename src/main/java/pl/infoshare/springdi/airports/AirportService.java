package pl.infoshare.springdi.airports;

import org.springframework.stereotype.Component;
import pl.infoshare.springdi.airports.model.Airport;
import pl.infoshare.springdi.airports.model.Continent;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class AirportService {

    private final CsvReader csvReader;
    private List<Airport> airports;

    public AirportService(CsvReader csvReader) {
        this.csvReader = csvReader;
        this.airports = new ArrayList<>();
    }

    @PostConstruct
    public void load() {
        FileReader source;
        try {
            source = new FileReader("airports.csv");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        CsvReader.RowMapper<Airport> rowMapper = input -> new Airport(input[0], input[1], Continent.valueOf(input[2]));
        this.airports = csvReader.parse(source, rowMapper);
    }

    public Optional<Airport> find(String iata) {
        return airports.stream()
                .filter(a -> a.getIata().equals(iata))
                .findFirst();
    }
}
