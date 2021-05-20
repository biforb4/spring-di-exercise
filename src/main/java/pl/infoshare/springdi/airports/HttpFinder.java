package pl.infoshare.springdi.airports;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
    public static final String APC_AUTH = "1c865af9d5";
    public static final String APC_AUTH_SECRET = "0b532ce6a35027b";
    private final HttpClient httpClient;


    @Override
    public Optional<Airport> find(String iata) {
        var request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create("https://www.air-port-codes.com/api/v1/single?iata=" + iata))
                .header("APC-Auth", APC_AUTH)
                .header("APC-Auth-Secret", APC_AUTH_SECRET)
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
