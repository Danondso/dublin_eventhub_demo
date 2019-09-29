package com.dublin.eventhub.demo.processor;

import com.dublin.eventhub.demo.model.EventPayload;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@NoArgsConstructor
@Service
class EventPayloadProcessor {

    void process(EventPayload eventPayload) {
        log.info("Hello! My name is {} and my favorite food is {}", eventPayload.getFirstName(), eventPayload.getFavoriteFood());
    }
}
