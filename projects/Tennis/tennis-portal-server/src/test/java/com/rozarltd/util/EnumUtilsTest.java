package com.rozarltd.util;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EnumUtilsTest {
    @Test
    public void shouldMapSourceEnumToDestinationEnum() {
        // when
        DestinationEnum resultEnum = EnumUtils.safeValueOf(SourceEnum.A, DestinationEnum.class);

        // then
        assertThat(resultEnum, is(DestinationEnum.A));
    }

    @Test
    public void shouldReturnNullWhenSourceEnumIsNull() {
        // when
        DestinationEnum resultEnum = EnumUtils.safeValueOf(null, DestinationEnum.class);

        // then
        assertThat(resultEnum, Matchers.nullValue());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenThereIsNoMatchForSourceEnumInDestinationEnum() {
        // when
        EnumUtils.safeValueOf(SourceEnum.B, DestinationEnum.class);
    }

    private enum SourceEnum {
        A, B
    }

    private enum DestinationEnum {
        A
    }
}
