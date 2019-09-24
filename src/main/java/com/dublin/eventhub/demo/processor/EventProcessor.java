package com.dublin.eventhub.demo.processor;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

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
        }
    }

    @Override
    public void onError(PartitionContext partitionContext, Throwable throwable) {
    }
}
