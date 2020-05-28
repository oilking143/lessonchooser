package com.example.testschoolschedule.model.server;

public class LessonResponse implements java.io.Serializable {
    private static final long serialVersionUID = 346118317155845630L;
    private LessonResponseStudentB[] studentB;
    private LessonResponseStudentA[] studentA;
    private LessonResponseStudentC[] studentC;

    public LessonResponseStudentB[] getStudentB() {
        return this.studentB;
    }

    public void setStudentB(LessonResponseStudentB[] studentB) {
        this.studentB = studentB;
    }

    public LessonResponseStudentA[] getStudentA() {
        return this.studentA;
    }

    public void setStudentA(LessonResponseStudentA[] studentA) {
        this.studentA = studentA;
    }

    public LessonResponseStudentC[] getStudentC() {
        return this.studentC;
    }

    public void setStudentC(LessonResponseStudentC[] studentC) {
        this.studentC = studentC;
    }
}
