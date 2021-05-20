package pl.infoshare.springdi.airports;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("airports.client")
@ConstructorBinding
@Value
class ClientProperties {
    String apcAuth;
    String apcAuthSecret;
    String url;
}
