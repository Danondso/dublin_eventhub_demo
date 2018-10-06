package com.dublin.eventhub.demo.controller;

import com.dublin.eventhub.demo.model.EventPayload;
import com.dublin.eventhub.demo.service.EventHubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final EventHubService eventHubService;
    private Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public Controller(EventHubService eventHubService) {
        this.eventHubService = eventHubService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String test() {
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/eventhub/test")
    public ResponseEntity<EventPayload> respond(@RequestBody EventPayload payload) {
        return new ResponseEntity(payload,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/eventhub/send")
    public ResponseEntity test(@RequestBody EventPayload payload) {

        try {
            log.info("Eventhub send endpoint called, sending {} to event hub..", payload.toString());
            eventHubService.sendEvent(payload);
        } catch (Exception e) {
            log.error("An error arose sending a message to event hub: " + e);
            return new ResponseEntity<Exception>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
