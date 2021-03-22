package com.example.leffa;

public class theatreInfo {
    private String movieName;
    private String movieStartTime;

    public theatreInfo(String name, String sTime) {
        movieName = name;
        movieStartTime = sTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieStartTime() {
        return movieStartTime;
    }
}
