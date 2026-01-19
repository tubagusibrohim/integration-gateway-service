package com.tubagus.integrationgateway.client;

import com.tubagus.integrationgateway.dto.DownstreamResponse;
import com.tubagus.integrationgateway.exception.IntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
public class DownstreamClient {

    private static final Logger log = LoggerFactory.getLogger(DownstreamClient.class);

    private final WebClient webClient;

    public DownstreamClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public DownstreamResponse callMockService() {
        try {
            return webClient
                    .get()
                    .uri("https://httpbin.org/json")
                    .retrieve()
                    .bodyToMono(DownstreamResponse.class)
                    .timeout(Duration.ofSeconds(3))
                    .block();
        } catch (Exception ex) {
            log.error("Downstream communication failure", ex);

            throw new IntegrationException(
                    "Downstream communication failure",
                    HttpStatus.BAD_GATEWAY
            );
        }
    }
}
