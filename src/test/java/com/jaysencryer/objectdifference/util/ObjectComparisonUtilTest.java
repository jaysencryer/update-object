package com.jaysencryer.objectdifference.util;

import com.jaysencryer.objectdifference.model.TestObject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectComparisonUtilTest {

    @Test
    void twoIdenticalObjectsHaveNoDifference() throws IllegalAccessException {
//        assertThat()
        TestObject to1 = TestObject.builder()
                .booleanProperty(true)
                .stringMap(Map.of("Test1", "test one"))
                .longProperty(42L)
                .stringProperty("Test This")
                .listOfLong(List.of(1L))
                .embeddedList(List.of(List.of("A","B","C"), List.of("1","2","3")))
                .build();

        Map<String, Object> differences = ObjectComparisonUtil.findDifference(to1,to1);

        assertThat(differences).hasSize(0);
    }

    @Test
    void differentObjectsReturnDifferences() throws IllegalAccessException {
        Map<String, String> stringMap = Map.of("Test1", "test one");
        TestObject to1 = TestObject.builder()
                .booleanProperty(true)
                .stringMap(stringMap)
                .longProperty(42L)
                .stringProperty("Test This")
                .listOfLong(List.of(1L))
                .embeddedList(List.of(List.of("A","B","C"), List.of("1","2","3")))
                .build();

        TestObject to2 = TestObject.builder()
                .booleanProperty(false)
                .stringMap(stringMap)
                .longProperty(42L)
                .stringProperty("Test This")
                .listOfLong(List.of(1L,2L))
                .embeddedList(List.of(List.of("A","B","3"), List.of("1","2","3")))
                .build();

        Map<String, Object> differences = ObjectComparisonUtil.findDifference(to1,to2);

        assertThat(differences).hasSize(3);
        assertThat(differences).containsEntry("booleanProperty", false);
        assertThat(differences).containsEntry("listOfLong", List.of(1L,2L));
        assertThat(differences).containsEntry("embeddedList",List.of(List.of("A","B","3"), List.of("1","2","3")));
    }
}
