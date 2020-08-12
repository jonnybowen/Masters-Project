package com.example.sdla_quiz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * No-arg Constructor
     */
    public Video() {

    }

    /**
     * Accessor method to get the title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Accessor method to set the title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor method to get the URL
     *
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Accessor method to set the URL
     *
     * @return url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Accessor method to get the subject id
     *
     * @return
     */
    public int getSubjectId() {
        return subjectId;
    }

    /**
     * Accessor method to set the subject id
     *
     * @param subjectId
     */
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Takes a full youtube video URL and returns the youtube video id as a string.
     * Taken from https://stackoverflow.com/questions/24048308/how-to-get-the-video-id-from-a-youtube-url-with-regex-in-java
     *
     * @param youTubeUrl - a youtube URL
     * @return videoID
     */
    public String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }
}
