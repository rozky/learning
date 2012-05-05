package com.rozarltd.spring.bean;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ZipPropertyValues implements PropertyValues {
    public static final String IGNORE_NAME = "$ignore";

    // Map<[PropertyName], PropertyValue>
    private Map<String, PropertyValue> properties;

    public ZipPropertyValues(String[] names, String[] values) {
        Assert.notNull(values);
		Assert.notNull(names);

		if (values.length != names.length) {
			throw new IllegalArgumentException("Field names must be same length as values: names="
					+ Arrays.asList(names) + ", values=" + Arrays.asList(values));
		}

        properties = new HashMap<String, PropertyValue>();
        String[] valuesClone = values.clone();
        for(int position = 0; position < names.length; position++) {
            String propName = names[position];
            if(!IGNORE_NAME.equalsIgnoreCase(propName)) {
                properties.put(propName, new PropertyValue(propName, valuesClone[position]));
            }
        }
    }

    private ZipPropertyValues() {
    }

    @Override
    public PropertyValue[] getPropertyValues() {
        Collection<PropertyValue> values = properties.values();
        return values.toArray(new PropertyValue[values.size()]);
    }

    @Override
    public PropertyValue getPropertyValue(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public PropertyValues changesSince(PropertyValues old) {
        return new ZipPropertyValues();
    }

    @Override
    public boolean contains(String propertyName) {
        return properties.containsValue(propertyName);
    }

    @Override
    public boolean isEmpty() {
        return this.properties == null || this.properties.isEmpty();
    }
}
