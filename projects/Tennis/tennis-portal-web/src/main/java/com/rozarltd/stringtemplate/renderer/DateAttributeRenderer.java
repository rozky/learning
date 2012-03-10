package com.rozarltd.stringtemplate.renderer;

import com.watchitlater.spring.Renderer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateAttributeRenderer implements Renderer {

    public static final Map<String, Integer> formatToInt =
            new HashMap<String, Integer>() {
                {
                    put("short", DateFormat.SHORT);
                    put("medium", DateFormat.MEDIUM);
                    put("long", DateFormat.LONG);
                    put("full", DateFormat.FULL);

                    put("date:short", DateFormat.SHORT);
                    put("date:medium", DateFormat.MEDIUM);
                    put("date:long", DateFormat.LONG);
                    put("date:full", DateFormat.FULL);

                    put("time:short", DateFormat.SHORT);
                    put("time:medium", DateFormat.MEDIUM);
                    put("time:long", DateFormat.LONG);
                    put("time:full", DateFormat.FULL);
                }
            };

    @Override
    public Class getTypeToRender() {
        return Date.class;
    }

    @Override
    public String toString(Object o) {
        return toString(o, "full");
    }

    public String toString(Object o, String formatString) {
        Date d;
        if (formatString == null) formatString = "short";
        if (o instanceof Calendar) d = ((Calendar) o).getTime();
        else d = (Date) o;
        Integer styleI = formatToInt.get(formatString);
        DateFormat f;
        if (styleI == null) f = new SimpleDateFormat(formatString);
        else {
            int style = styleI.intValue();
            if (formatString.startsWith("date:")) f = DateFormat.getDateInstance(style);
            else if (formatString.startsWith("time:")) f = DateFormat.getTimeInstance(style);
            else f = DateFormat.getDateTimeInstance(style, style);
        }
        return f.format(d);
    }

}
