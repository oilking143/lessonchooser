package com.example.testschoolschedule.Event;

import com.example.testschoolschedule.model.server.LessonResponse;

public class LessonEvent {

    private int status;
    private String message;
    private LessonResponse response;

    public LessonEvent(int status, String message, LessonResponse response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LessonResponse getResponse() {
        return response;
    }
}
