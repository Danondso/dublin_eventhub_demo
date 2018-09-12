package com.dublin.eventhub.demo.configuration;

import com.dublin.eventhub.demo.processor.EventPayloadProcessor;
import com.dublin.eventhub.demo.processor.EventProcessor;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import com.microsoft.azure.eventprocessorhost.EventProcessorHost;
import com.microsoft.azure.eventprocessorhost.IEventProcessorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.Executors;

@Configuration
public class EventHubConfig {


    @Value("${eventHub.namespace}")
    private String namespace;

    @Value("${eventHub.eventHubName}")
    private String eventHubName;

    @Value("${eventHub.sharedAccessKey}")
    private String sharedAccessKey;

    @Value("${eventHub.sharedAccessKeyName}")
    private String sharedAccessKeyName;

    @Value("${eventHub.storage.consumerGroupName}")
    private String consumerGroupName;

    @Value("${eventHub.storage.storageConnectionString}")
    private String storageConnectionString;

    @Value("${eventHub.storage.storageContainerName}")
    private String storageContainerName;

    @Value("${eventHub.storage.hostNamePrefix}")
    private String hostNamePrefix;

    private String createEventHubConnectionString() {
        return new ConnectionStringBuilder()
                .setNamespaceName(namespace)
                .setEventHubName(eventHubName)
                .setSasKeyName(sharedAccessKeyName)
                .setSasKey(sharedAccessKey).toString();
    }

    @Bean
    public IEventProcessorFactory<EventProcessor> getEventProcessorFactory() {
        return partitionContext -> new EventProcessor(new EventPayloadProcessor());
    }

    @Bean
    public EventHubClient setupEventHubConnection() throws IOException, EventHubException {
        return EventHubClient.createSync(createEventHubConnectionString(), Executors.newSingleThreadExecutor());
    }

    @Bean
    public EventProcessorHost getEventProcessorHost() {
        return new EventProcessorHost(
                EventProcessorHost.createHostName(hostNamePrefix),
                eventHubName,
                consumerGroupName,
                createEventHubConnectionString(),
                storageConnectionString,
                storageContainerName);
    }

}
