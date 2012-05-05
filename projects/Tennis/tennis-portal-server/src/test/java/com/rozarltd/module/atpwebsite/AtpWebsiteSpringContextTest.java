package com.rozarltd.module.atpwebsite;

import com.rozarltd.module.atpwebsite.service.AtpWebsiteClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AtpWebsiteSpringContext.class)
public class AtpWebsiteSpringContextTest {
    @Autowired private AtpWebsiteClient atpWebsiteService;

    @Test
    public void shouldWireUp() {
        // given
        // when
        // then
    }
}
