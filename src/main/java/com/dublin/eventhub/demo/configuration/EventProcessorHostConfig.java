package com.dublin.eventhub.demo.configuration;

import com.dublin.eventhub.demo.exception.ErrorNotificationHandler;
import com.dublin.eventhub.demo.processor.EventPayloadProcessor;
import com.dublin.eventhub.demo.processor.EventProcessor;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventprocessorhost.EventProcessorHost;
import com.microsoft.azure.eventprocessorhost.EventProcessorOptions;
import com.microsoft.azure.eventprocessorhost.IEventProcessorFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;


@Component
public class EventProcessorHostConfig {

    private final EventProcessorHost eventProcessorHost;
    private final IEventProcessorFactory<EventProcessor> iEventProcessorFactory;

    private int batchSize;
    private Logger log = LoggerFactory.getLogger(EventProcessorHostConfig.class);


    @Autowired
    public EventProcessorHostConfig(EventProcessorHost eventProcessorHost, @Value("${eventHub.maxBatchSize}") int configBatchSize, IEventProcessorFactory<EventProcessor> iEventProcessorFactory) {
        this.eventProcessorHost = eventProcessorHost;
        this.batchSize = configBatchSize;
        this.iEventProcessorFactory = iEventProcessorFactory;
    }


    @PostConstruct
    public void run() throws ExecutionException, InterruptedException {

        log.info("Setting up event hub {}", eventProcessorHost.getHostName());
        EventProcessorOptions options = new EventProcessorOptions();
        options.setExceptionNotification(new ErrorNotificationHandler());
        options.setMaxBatchSize(batchSize);

        eventProcessorHost.registerEventProcessorFactory(iEventProcessorFactory, options).get();

    }

}
