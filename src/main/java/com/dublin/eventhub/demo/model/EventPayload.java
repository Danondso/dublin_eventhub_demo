package com.dublin.eventhub.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventPayload implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    Map<String, String> eventPayloadDetails;
}
