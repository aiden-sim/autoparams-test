package com.aiden.domain.study;

import autoparams.AutoSource;
import autoparams.CsvAutoSource;
import autoparams.MethodAutoSource;
import autoparams.ValueAutoSource;
import autoparams.customization.Freeze;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class AutoParamsTest {

    @AutoSource
    @ParameterizedTest
    void testFreeze(@Freeze String arg1, String arg2, ValueContainer arg3) {
        log.info("arg1 : {}, arg2 : {}, arg3 : {}", arg1, arg2, arg3);

        assertEquals(arg1, arg2);
        assertEquals(arg1, arg3.getValue());
    }

    @ParameterizedTest
    @ValueAutoSource(strings = {"foo"})
    void testValueAutoSource(String arg1, String arg2) {
        log.info("arg1 : {}, arg2 : {}", arg1, arg2);

        assertEquals("foo", arg1);
        assertNotEquals(arg1, arg2);
    }

    @ParameterizedTest
    @ValueAutoSource(strings = {"foo"})
    void testValueAutoSourceWithFreeze(@Freeze String arg1, String arg2, ValueContainer arg3) {
        assertEquals("foo", arg2);
        assertEquals("foo", arg3.getValue());
    }

    @ParameterizedTest
    @CsvAutoSource({"16, foo"})
    void testCsvAutoSource(int arg1, String arg2, String arg3) {
        log.info("arg1 : {}, arg2 : {}, arg3 : {}", arg1, arg2, arg3);

        assertEquals(16, arg1);
        assertEquals("foo", arg2);
        assertNotEquals(arg2, arg3);
    }

    @ParameterizedTest
    @MethodAutoSource("factoryMethod")
    void testMethodAutoSource(int arg1, String arg2, String arg3) {
        log.info("arg1 : {}, arg2 : {}, arg3 : {}", arg1, arg2, arg3);

        assertEquals(16, arg1);
        assertEquals("foo", arg2);
        assertNotEquals(arg2, arg3);
    }

    static Stream<Arguments> factoryMethod() {
        return Stream.of(Arguments.of(16, "foo"));
    }

    @ParameterizedTest
    @AutoSource
    void testRange(@Min(1) @Max(10) int value) {
        assertTrue(value >= 1);
        assertTrue(value <= 10);
    }
}
