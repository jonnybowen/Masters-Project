package com.example.sdla_quiz;

/**
 * Custom data class to represent a video.
 * Consists of a title, url and is tied to a subject.
 */
public class Video {

    //Declare vars
    private String title;
    private String url;
    private int subjectId;

    /**
     * Constructor
     *
     * @param title
     * @param url
     * @param subjectId
     */
    public Video(String title, String url, int subjectId) {
        this.title = title;
        this.url = url;
        this.subjectId = subjectId;
    }

    /**
     * Accessor method to get the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Accessor method to set the title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor method to get the URL
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Accessor method to get the subject id
     * @return
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * Accessor method to set the subject id
     * @param subjectId
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
