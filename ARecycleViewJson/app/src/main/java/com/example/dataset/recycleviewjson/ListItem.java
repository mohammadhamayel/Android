package com.example.dataset.recycleviewjson;

public class ListItem {
   private String Code;
   private  String Name;


    public ListItem(String Code, String Name) {
        this.Code= Code;
        this.Name = Name;

    }

    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

}
