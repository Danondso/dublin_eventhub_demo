package com.dublin.eventhub.demo.processor;

import com.dublin.eventhub.demo.model.EventPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

@Slf4j
@NoArgsConstructor
@Service
public class EventProcessor implements IEventProcessor {

    @Override
    public void onOpen(PartitionContext partitionContext) throws Exception {

    }

    @Override
    public void onClose(PartitionContext partitionContext, CloseReason closeReason) throws Exception {

    }

    @Override
    public void onEvents(PartitionContext partitionContext, Iterable<EventData> iterable) throws Exception {
        for(EventData event: iterable) {
           EventPayload eventPayload = (EventPayload) SerializationUtils.deserialize(event.getBytes());
            log.info("Hello! My name is {} and my favorite food is {}", eventPayload.getFirstName(), eventPayload.getFavoriteFood());
            //partitionContext.checkpoint(event);
        }
    }

    @Override
    public void onError(PartitionContext partitionContext, Throwable throwable) {
    }
}
