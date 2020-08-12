package com.example.sdla_quiz;

import org.junit.Test;



import static org.junit.Assert.*;

public class VideoTest {

    /**
     * Tests to verify title getters and setters.
     */
    @org.junit.Test
    public void testTitleGetterSetter() {
        //Match
        String input = "TEST TITLE";
        String expected = "TEST TITLE";
        Video video = new Video();
        video.setTitle(input);
        assert video.getTitle() == expected;

        //No Match
        String input2 = "TEST TITLE";
        String expected2 = "TEST TITLE DIFFERENT";
        Video video2 = new Video();
        video2.setTitle(input2);
        assertTrue(video2.getTitle() != expected2);
    }

    /**
     * A test to verify the URL getters and setters, as well as the youtubeID extraction method.
     */
    @Test
    public void testURL(){
        //As intended
        Video v = new Video("Test video", "https://www.youtube.com/watch?v=JAzm263vp1s", 3);
        String expected = "JAzm263vp1s";
        String output = null;
        output = v.getYouTubeId(v.getUrl());
        assertEquals (output, expected);

        //Different URL Format
        Video v2 = new Video("Test video", "www.youtube.com/watch?v=JAzm263vp1s", 3);
        String expected2 = "JAzm263vp1s";
        String output2 = null;
        output2 = v2.getYouTubeId(v2.getUrl());
        assertEquals (output2, expected2);
    }

    /**
     * Tests to verify subjectId getters and setters.
     */
    @org.junit.Test
    public void testSubjectIdGetterSetter() {
        //Standard Use
        int input = 3;
        int expected = 3;
        Video Video = new Video();
        Video.setSubjectId(input);
        assert Video.getSubjectId() == expected;

        //Not equal
        int input2 = 3;
        int expected2 = 1;
        Video Video2 = new Video();
        Video2.setSubjectId(input2);
        assertTrue(Video2.getSubjectId() != expected2);
    }
}