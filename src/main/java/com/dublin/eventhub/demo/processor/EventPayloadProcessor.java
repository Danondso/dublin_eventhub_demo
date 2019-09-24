package com.dublin.eventhub.demo.processor;

import com.dublin.eventhub.demo.model.EventPayload;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventPayloadProcessor {

    public EventPayloadProcessor() {}

    public void process(EventPayload eventPayload) {
        log.info("Object read in from eventHub: {}", eventPayload);
    }
}
