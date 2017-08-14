package com.example.measuresdk.common;

import java.lang.reflect.Type;

/**
 * Created by ysx on 2017/8/9.
 */

public class ParamField {
    private String name;

    private Object object;

    private String value;

    private Type type;

    public ParamField(String name) {
        this.name = name;
    }

    public ParamField(String name,String value) {
        this.name = name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
