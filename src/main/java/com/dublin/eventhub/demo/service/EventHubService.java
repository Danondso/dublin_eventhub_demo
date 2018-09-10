package com.dublin.eventhub.demo.service;

import com.dublin.eventhub.demo.controller.Controller;
import com.dublin.eventhub.demo.model.EventPayload;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.Objects;

@Slf4j
@Service
public class EventHubService {


    private final EventHubClient eventHubClient;
    private Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public EventHubService(EventHubClient eventHubClient) {
        this.eventHubClient = eventHubClient;
    }

    public void sendEvent(EventPayload test) throws EventHubException {
       byte[] bytes =  test.toString().getBytes();
        log.info("Sending message to the event hub {}", eventHubClient.getEventHubName());
        eventHubClient.send(EventData.create(Objects.requireNonNull(bytes)), test.toString());

        log.info("Successfully sent message, closing connection..");
        eventHubClient.closeSync();

    }
}
