package com.dublin.eventhub.demo;

import com.dublin.eventhub.demo.exception.ErrorNotificationHandler;
import com.dublin.eventhub.demo.processor.EventProcessor;
import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import com.microsoft.azure.eventprocessorhost.EventProcessorHost;
import com.microsoft.azure.eventprocessorhost.EventProcessorOptions;
import com.microsoft.azure.eventprocessorhost.PartitionManagerOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {


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

    private String createEventHubConnectionString()
    {
        return new ConnectionStringBuilder()
                .setNamespaceName(namespace)
                .setEventHubName(eventHubName)
                .setSasKeyName(sharedAccessKeyName)
                .setSasKey(sharedAccessKey).toString();
    }

    @Bean
    public EventHubClient setupEventHubConnection() throws IOException, EventHubException { return EventHubClient.createSync(createEventHubConnectionString(), Executors.newSingleThreadExecutor()); }

    @PostConstruct
    public void setupEventProcessorHost() throws ExecutionException, InterruptedException {
        EventProcessorHost host = new EventProcessorHost(
                EventProcessorHost.createHostName(hostNamePrefix),
                eventHubName,
                consumerGroupName,
                createEventHubConnectionString(),
                storageConnectionString,
                storageContainerName);

        EventProcessorOptions options = new EventProcessorOptions();
        options.setExceptionNotification(new ErrorNotificationHandler());

        host.registerEventProcessor(EventProcessor.class, options).get();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
