package com.tubagus.integrationgateway.service;

import com.tubagus.integrationgateway.client.DownstreamClient;
import com.tubagus.integrationgateway.dto.DownstreamResponse;
import org.springframework.stereotype.Service;

@Service
public class DownstreamService {

    private final DownstreamClient downstreamClient;

    public DownstreamService(DownstreamClient downstreamClient) {
        this.downstreamClient = downstreamClient;
    }

    public DownstreamResponse callDownstream() {
        return downstreamClient.callMockService();
    }
}
