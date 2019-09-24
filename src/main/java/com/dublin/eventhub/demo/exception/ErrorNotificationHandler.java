package com.dublin.eventhub.demo.exception;

import java.util.function.Consumer;

import com.microsoft.azure.eventprocessorhost.ExceptionReceivedEventArgs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorNotificationHandler implements Consumer<ExceptionReceivedEventArgs> {

    @Override
    public void accept(ExceptionReceivedEventArgs t) {
        log.error("SAMPLE: Host " + t.getHostname() + " received general error notification during " + t.getAction() + ": " + t.getException().toString());
    }
}