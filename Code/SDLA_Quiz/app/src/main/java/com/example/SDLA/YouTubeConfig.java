package com.example.SDLA;

/**
 * A custom data-helper class to store the youtube API key for this package.
 */
public class YouTubeConfig {

    public YouTubeConfig() {
    }

    // The API key.
    private static final String API_KEY = "AIzaSyBmGymWqoWwJU1-qA0jdfvVnZhIJ9PPRtA";

    /**
     * Accessor method to return the api key as a string.
     *
     * @return
     */
    public static String getApiKey() {
        return API_KEY;
    }
}
