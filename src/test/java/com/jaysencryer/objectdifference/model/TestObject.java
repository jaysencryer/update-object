package com.jaysencryer.objectdifference.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TestObject {
    private String stringProperty;
    private Long longProperty;
    private boolean booleanProperty;
    List<List<String>> embeddedList;
    List<Long> listOfLong;
    Map<String, String> stringMap;
}
