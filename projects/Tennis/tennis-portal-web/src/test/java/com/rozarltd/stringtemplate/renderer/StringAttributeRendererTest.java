package com.rozarltd.stringtemplate.renderer;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class StringAttributeRendererTest {

    @Test
    public void shouldConvertToSnakeCase() {
        assertThat(new StringAttributeRenderer().toString("Match Odds (Inc Ot)", "snakecase"),
                Matchers.equalTo("match_odds_inc_ot"));
    }
}
