package com.rozarltd.domain;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class PageTest {
    @Test
    public void shouldCalculatePageOffsetCorrectly() {
        assertThat(new Page(1, 10).getOffset()).isEqualTo(1);
        assertThat(new Page(0, 10).getOffset()).isEqualTo(1);
        assertThat(new Page(-1, 10).getOffset()).isEqualTo(1);
        assertThat(new Page(null, null).getOffset()).isEqualTo(1);
    }
}
