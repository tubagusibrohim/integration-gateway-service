package com.tubagus.integrationgateway.controller;

import com.tubagus.integrationgateway.dto.DownstreamResponse;
import com.tubagus.integrationgateway.service.DownstreamService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entry point for external integration requests.
 * Acts as an API gateway / orchestration layer.
 */
@RestController
@RequestMapping("/api/v1/gateway")
public class GatewayController {

    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);

    private final DownstreamService downstreamService;

    public GatewayController(DownstreamService downstreamService) {
        this.downstreamService = downstreamService;
    }

    /**
     * Health endpoint for readiness & liveness checks.
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        log.info("Health endpoint invoked");
        return ResponseEntity.ok("OK");
    }

    /**
     * Example endpoint that calls downstream service.
     */
    @GetMapping("/downstream")
    public ResponseEntity<DownstreamResponse> callDownstream() {
        log.info("Calling downstream service");
        DownstreamResponse response = downstreamService.callDownstream();
        log.info("Downstream call completed");
        return ResponseEntity.ok(response);
    }
}
