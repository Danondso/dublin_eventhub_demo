package com.dublin.eventhub.demo.exception;

import java.util.function.Consumer;

import com.dublin.eventhub.demo.controller.Controller;
import com.microsoft.azure.eventprocessorhost.ExceptionReceivedEventArgs;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class ErrorNotificationHandler implements Consumer<ExceptionReceivedEventArgs> {

    private Logger log = LoggerFactory.getLogger(Controller.class);

    @Override
    public void accept(ExceptionReceivedEventArgs t) {
        log.error("SAMPLE: Host " + t.getHostname() + " received general error notification during " + t.getAction() + ": " + t.getException().toString());
    }
}