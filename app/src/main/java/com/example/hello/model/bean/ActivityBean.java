package com.example.hello.model.bean;

/**
 * Created by ysx on 2017/10/9.
 */

public class ActivityBean {

    private String title;
    private Class<?> aClass;

    public ActivityBean(String title, Class<?> aClass) {
        this.title = title;
        this.aClass = aClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }
}
