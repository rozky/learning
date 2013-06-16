package com.rozarltd.roger.config;

import com.rozarltd.stringtemplate.renderer.DateAttributeRenderer;
import com.rozarltd.stringtemplate.renderer.DoubleRenderer;
import com.rozarltd.stringtemplate.renderer.PageletRenderer;
import com.rozarltd.stringtemplate.renderer.StringAttributeRenderer;
import com.rozarltd.stringtemplate.renderer.TimestampAttributeRenderer;
import com.watchitlater.spring.Renderer;
import com.watchitlater.spring.StringTemplateViewResolver;
import com.watchitlater.spring.log.Slf4jStringTemplateErrorListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StringTemplateConfig {

    @Bean
    public ViewResolver viewResolver() {
        StringTemplateViewResolver viewResolver = new StringTemplateViewResolver();
        viewResolver.setTemplateErrorListener(templateErrorListener());
        viewResolver.setTemplateRoot("/WEB-INF/stringtemplate/views/");
        viewResolver.setSharedRoot("/WEB-INF/stringtemplate/shared");
        List<Renderer> renderers = new ArrayList<Renderer>();
        renderers.add(new PageletRenderer());
        renderers.add(new DateAttributeRenderer());
        renderers.add(new TimestampAttributeRenderer());
        renderers.add(new DoubleRenderer());
        renderers.add(new StringAttributeRenderer());
        viewResolver.setRenderers(renderers);
        return viewResolver;
    }

    @Bean
    public Slf4jStringTemplateErrorListener templateErrorListener() {
        return new Slf4jStringTemplateErrorListener();
    }
}
