package com.dublin.eventhub.demo.processor;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventDataBatch;
import com.microsoft.azure.eventhubs.PayloadSizeExceededException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDataBatchImpl implements EventDataBatch {

    private ArrayList<EventData> arrayList;

    @Override
    public int getSize() {
        return arrayList.size();
    }



    @Override
    public boolean tryAdd(EventData eventData) throws PayloadSizeExceededException {

        if(arrayList == null)
            this.arrayList = new ArrayList<>();

        arrayList.add(eventData);
        return true;
    }
}
