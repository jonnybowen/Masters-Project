package com.example.sdla_quiz;

public class Note {
    private String title;
    private String content;
    private String timestamp;
    private String subjectID;

    public Note(String title, String content, String timestamp, String subjectID) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.subjectID = subjectID;
    }

    public Note() {
    }







    //Accessor Methods

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
