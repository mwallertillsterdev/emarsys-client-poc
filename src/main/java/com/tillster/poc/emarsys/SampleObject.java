package com.tillster.poc.emarsys;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleObject {

    private String itemId;
    private String price;
    private String orderId;
    private String timestamp;
    private String customerIdentifier;
    private String quantity;
}
