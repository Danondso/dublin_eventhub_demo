package com.dublin.eventhub.demo;

import com.microsoft.azure.eventhubs.PayloadSizeExceededException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EventHubDemoIT {

    @Test
    public void testEventHubSends() throws PayloadSizeExceededException {

    }
}
