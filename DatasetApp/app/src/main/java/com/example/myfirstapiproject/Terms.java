package com.example.myfirstapiproject;

public class Terms {
    private String code;
    private String name;
    private String name2;

    public Terms(String code, String name, String name2){
        this.code= code;
        this.name = name;
        this.name2= name2;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }
}
