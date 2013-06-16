package com.rozarltd.stringtemplate.renderer;

import java.sql.Timestamp;

public class TimestampAttributeRenderer extends DateAttributeRenderer {

    @Override
    public Class getTypeToRender() {
        return Timestamp.class;
    }
}
