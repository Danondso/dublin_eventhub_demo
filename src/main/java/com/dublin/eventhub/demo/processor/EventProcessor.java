package com.dublin.eventhub.demo.processor;

import com.dublin.eventhub.demo.model.EventPayload;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

public class EventProcessor implements IEventProcessor {

    @Autowired
    EventPayloadProcessor eventPayloadProcessor;

    private Logger log = LoggerFactory.getLogger(EventProcessor.class);

    @Override
    public void onOpen(PartitionContext context) {
        log.info("SAMPLE: Partition OPEN" + context.getPartitionId() + " is opening");
    }

    @Override
    public void onClose(PartitionContext context, CloseReason reason) {
        log.info("SAMPLE: Partition CLOSE" + context.getPartitionId() + " is closing for reason " + reason.toString());
    }

    @Override
    public void onError(PartitionContext context, Throwable error) {
        log.info("SAMPLE: Partition ERROR" + context.getPartitionId() + " onError: " + error.toString());
    }

    @Override
    public void onEvents(PartitionContext context, Iterable<EventData> events) {
        log.info("SAMPLE: Partition PROCESSING " + context.getPartitionId() + " got event batch");

        for (EventData data : events) {
            try {
                log.info("Serializing event data to object..");
                EventPayload eventPayload = (EventPayload) SerializationUtils.deserialize(data.getBytes());
                log.info("Serialization successful! Sending to event payload processor..");
                eventPayloadProcessor.process(eventPayload);
                //log.info("SAMPLE: Partition " + context.getPartitionId() + " checkpointing at " + data.getSystemProperties().getOffset() + "," + data.getSystemProperties().getSequenceNumber());
                context.checkpoint(data);

            } catch (Exception e) {
                log.info("Processing failed for an event: " + e.toString());
            }
        }
    }
}
