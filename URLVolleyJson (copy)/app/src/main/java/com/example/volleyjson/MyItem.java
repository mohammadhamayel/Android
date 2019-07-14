package com.example.volleyjson;

public class MyItem {
    private String userId;
    private String id;
    private String title;
    private boolean completed;
    public MyItem(String userId, String id, String title,boolean completed)
    {
        this.userId = userId;
        this.id=id;
        this.title=title;
        this.completed=completed;
    }
    public Boolean isCompleted() {
        /*oolean check = false;
        if (completed =="true")
            check = true;*/
        return completed;
    }
    public void setCompleted(boolean completed) {
        /*if (completed == true)
        this.completed = "true";
        else
            this.completed = "false";*/
        this.completed = completed;

    }
    public String getTitle() {
        return title;
    }

    public String getUserId(){
        return userId;
    }

    public  String getId(){
        return id;
    }

    public String getCompleted(){
        String check = "false";
        if (completed ==true)
            check = "true";
        return check;

    }

}