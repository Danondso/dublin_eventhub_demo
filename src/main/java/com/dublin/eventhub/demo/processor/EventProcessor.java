package com.dublin.eventhub.demo.processor;

import com.dublin.eventhub.demo.model.EventPayload;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class EventProcessor implements IEventProcessor {

    private final EventPayloadProcessor eventPayloadProcessor;

    @Autowired
    public EventProcessor(EventPayloadProcessor eventPayloadProcessor) {
        this.eventPayloadProcessor = eventPayloadProcessor;
    }

    @Override
    public void onOpen(PartitionContext partitionContext) throws Exception {

    }

    @Override
    public void onClose(PartitionContext partitionContext, CloseReason closeReason) throws Exception {

    }

    @Override
    public void onEvents(PartitionContext partitionContext, Iterable<EventData> iterable) throws Exception {
        for(EventData event: iterable) {
            log.info(event.toString());
        }
    }

    @Override
    public void onError(PartitionContext partitionContext, Throwable throwable) {
    }
}
