package com.dublin.eventhub.demo.processor;

import com.dublin.eventhub.demo.model.EventPayload;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

@NoArgsConstructor
@Service
public class EventProcessor implements IEventProcessor {

    private EventPayloadProcessor eventPayloadProcessor;

    @Override
    public void onOpen(PartitionContext partitionContext) {
        eventPayloadProcessor = new EventPayloadProcessor();
    }

    @Override
    public void onClose(PartitionContext partitionContext, CloseReason closeReason) {

    }

    @Override
    public void onEvents(PartitionContext partitionContext, Iterable<EventData> iterable) {

        for(EventData event: iterable) {
           EventPayload eventPayload = (EventPayload) SerializationUtils.deserialize(event.getBytes());
            eventPayloadProcessor.process(eventPayload);
            partitionContext.checkpoint(event);
        }
    }

    @Override
    public void onError(PartitionContext partitionContext, Throwable throwable) {
    }
}
