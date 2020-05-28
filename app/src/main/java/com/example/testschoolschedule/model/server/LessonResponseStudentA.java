package com.example.testschoolschedule.model.server;

public class LessonResponseStudentA implements java.io.Serializable {
    private static final long serialVersionUID = 8661617707489067736L;
    private String color;
    private String lesson;

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLesson() {
        return this.lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
