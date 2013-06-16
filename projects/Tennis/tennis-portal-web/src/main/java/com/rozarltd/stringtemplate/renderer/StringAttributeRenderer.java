package com.rozarltd.stringtemplate.renderer;

import com.watchitlater.spring.Renderer;

public class StringAttributeRenderer implements Renderer {
    private static final String LOWER_CASE = "lowercase";
    private static final String SNAKE_CASE = "snakecase";

    @Override
    public Class getTypeToRender() {
        return String.class;
    }

    @Override
    public String toString(Object o) {
        return o.toString();
    }

    public String toString(Object o, String formatString) {
        if(SNAKE_CASE.equals(formatString)) {
            return toSnakeCase((String) o);
        } else if (LOWER_CASE.equals(formatString)) {
            return toLowerCase((String) o);
        }

        return toString(o);
    }

    private String toLowerCase(String input) {
        return input.toLowerCase();
    }

    private String toSnakeCase(String input) {
        return toLowerCase(input.replaceAll("[\\(\\)]", "").replaceAll("[ /.]", "_"));
    }
}
