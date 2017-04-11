package net.serenitybdd.dojo.supermarket;

import org.assertj.core.api.StrictAssertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class JavaEightExperiment {

    @Test
    public void should_add_all_numbers_in_the_list() throws Exception {

        //Given
        List<Integer> integers = Arrays.asList(1,2,4);

        //When
        Integer result = integers.stream().reduce(
                0,
                (accumlator, item) -> accumlator + item
        );

        //Then
        assertThat(result).isEqualTo(7);
    }

    @Test
    public void should_multiple_each_item_in_list_by_three() throws Exception {

        //Given
        List<Integer> integers = Arrays.asList(1,2,4);

        //When
        List<Integer> result = integers.stream()
                .map(item -> item*3)
                //.mapToInt(item -> item*3)
                //.sum(); //then can use sum and etc like sql agregates but get back an integer not a list
                .collect(toList());

        //Then
        assertThat(result).isEqualTo(Arrays.asList(3,6,12));

    }

    Integer itemByThree(Integer item) {
        return item*3;
    }




}


