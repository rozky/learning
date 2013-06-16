package com.rozarltd.stringtemplate.renderer;

import com.watchitlater.spring.Renderer;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class DoubleRenderer implements Renderer {

    public static final BigDecimal HUNDRED = BigDecimal.valueOf(100l);

    public static final Map<String, Format> formats =
            new HashMap<String, Format>() {
                {
                    put("doubleScale2", getDoubleFormat(2));
                }
            };

    @Override
    public Class getTypeToRender() {
        return Double.class;
    }

    @Override
    public String toString(Object o) {
        return toString(o, null);
    }

    @Override
    public String toString(Object o, String formatName) {
        if("percent".equals(formatName)) {
            Double value = (Double) o;
            return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(HUNDRED).toString() + "%";
        }

        if(formatName == null || formats.get(formatName) == null) {

            return o.toString();
        }

        return formats.get(formatName).format(o);
    }

    private static Format getDoubleFormat(int fraction) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);

        return format;
    }
}
