package pl.infoshare.springdi.airports;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.infoshare.springdi.airports.model.Airport;
import pl.infoshare.springdi.airports.model.Continent;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Component
@HttpFinderQualifier
@Primary
@AllArgsConstructor
public class HttpFinder implements AirportFinder {
    private final HttpClient httpClient;
    private final ClientProperties clientProperties;


    @Override
    public Optional<Airport> find(String iata) {
        var request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(clientProperties.getUrl() + iata))
                .header("APC-Auth", clientProperties.getApcAuth())
                .header("APC-Auth-Secret", clientProperties.getApcAuthSecret())
                .build();

        HttpResponse<String> result;
        try {
            result = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var obj = new JSONObject(result.body()).getJSONObject("airport");

            return Optional.of(new Airport(
                    obj.getString("iata"),
                    obj.getString("name"),
                    Continent.valueOf(obj.getJSONObject("continent").getString("abbr"))
            ));
        } catch (IOException | InterruptedException | JSONException e) {
            return Optional.empty();
        }
    }
}
