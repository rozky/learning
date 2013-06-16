package com.rozarltd.stringtemplate.renderer;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class DoubleRendererTest {

    @Test
    public void shouldFormatToPercents() {
        DoubleRenderer renderer = new DoubleRenderer();
        assertThat(renderer.toString(0.145d, "percent")).isEqualTo("14.00%");
        assertThat(renderer.toString(0.0125d, "percent")).isEqualTo("1.00%");
        assertThat(renderer.toString(0.0025d, "percent")).isEqualTo("0.00%");
        assertThat(renderer.toString(0.0075d, "percent")).isEqualTo("1.00%");
    }
}
