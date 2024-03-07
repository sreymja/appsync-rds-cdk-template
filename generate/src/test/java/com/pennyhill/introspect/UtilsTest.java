package com.pennyhill.introspect;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UtilsTest {

    @ParameterizedTest
    @CsvSource({"statuses,status", "priorities,priority", "users,user"})
    void testSingular(String input, String expected) throws Exception {
        var try1 = Utils.singular(input);
        var try2 = Utils.singular(input);
        var try3 = Utils.singular(input);
        var try4 = Utils.singular(input);

        assertThat(try1).isEqualTo(expected);
        assertThat(try2).isEqualTo(expected);
        assertThat(try3).isEqualTo(expected);
        assertThat(try4).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"statuses,Status", "priorities,Priority", "users,User"})
    void testSingularCapitalize(String input, String expected) throws Exception {
        var try1 = Utils.singularCapitalize(input);
        var try2 = Utils.singularCapitalize(input);
        var try3 = Utils.singularCapitalize(input);
        var try4 = Utils.singularCapitalize(input);

        assertThat(try1).isEqualTo(expected);
        assertThat(try2).isEqualTo(expected);
        assertThat(try3).isEqualTo(expected);
        assertThat(try4).isEqualTo(expected);
    }
}
