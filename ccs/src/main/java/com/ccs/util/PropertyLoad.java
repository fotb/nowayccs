package com.ccs.util;


import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class PropertyLoad {
    private static PropertyResourceBundle propResourceBundle;

    protected void build() {
        propResourceBundle = (PropertyResourceBundle) ResourceBundle.getBundle("ccs");
    }

    private PropertyLoad() {
        build();
    }

    private static PropertyLoad instance = new PropertyLoad();

    public static PropertyLoad getInstance() {
        if (instance == null) {
            instance = new PropertyLoad();
        }

        return instance;
    }

    public String getString(final String name) {
        return PropertyLoad.propResourceBundle.getString(name);
    }

    @SuppressWarnings("rawtypes")
	public Enumeration getKeys() {
        return PropertyLoad.propResourceBundle.getKeys();
    }
}
