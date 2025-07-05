package com.in28minues.microservices.limits_service.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Limits {

    private int minimumLimit;
    private int maximumLimit;
}
