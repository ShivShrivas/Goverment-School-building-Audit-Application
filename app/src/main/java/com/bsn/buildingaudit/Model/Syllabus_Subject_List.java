package com.bsn.buildingaudit.Model;

public class Syllabus_Subject_List {
    String classes,subject,chapter;

    public Syllabus_Subject_List() {
    }

    public Syllabus_Subject_List(String classes, String subject, String chapter) {
        this.classes = classes;
        this.subject = subject;
        this.chapter = chapter;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }
}
