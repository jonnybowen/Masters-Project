package com.example.sdla_quiz;

public class Video {
    private String title;
    private String url;
    private int subjectId;

    public Video(){

    }

    public Video(String title, String url, int subjectId){
        this.title = title;
        this.url = url;
        this.subjectId = subjectId;
    }

    //accessor methods

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
