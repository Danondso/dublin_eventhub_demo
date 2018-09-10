package com.dublin.eventhub.demo.processor;

import com.dublin.eventhub.demo.model.EventPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventPayloadProcessor {

    private Logger log = LoggerFactory.getLogger(EventPayloadProcessor.class);

    public void process(EventPayload eventPayload) {
        log.info("Object read in from eventHub: {}", eventPayload);
    }
}
