package com.dublin.eventhub.demo.configuration;

import com.dublin.eventhub.demo.exception.ErrorNotificationHandler;
import com.dublin.eventhub.demo.processor.EventProcessor;
import com.microsoft.azure.eventprocessorhost.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;


@Component
public class EventProcessorHostService {

    private final EventProcessorHost eventProcessorHost;
    private Logger log = LoggerFactory.getLogger(EventProcessorHostService.class);

    @Autowired
    public EventProcessorHostService(EventProcessorHost eventProcessorHost) {
        this.eventProcessorHost = eventProcessorHost;
    }

    @PostConstruct
    public void run() throws ExecutionException, InterruptedException {
        log.info("Setting up event hub {}", eventProcessorHost.getHostName());
        EventProcessorOptions options = new EventProcessorOptions();
        options.setExceptionNotification(new ErrorNotificationHandler());
        eventProcessorHost.registerEventProcessor(EventProcessor.class, options).get();
    }
}
