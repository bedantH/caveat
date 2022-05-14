package com.example.mad_project;

public class Suggestion {
    int id;
    String title;
    String course;
    String tags;
    String suggestion;
    boolean isAnonymous;

    public Suggestion(){
        super();
    }

    public Suggestion(String title, String course, String tags, String suggestion, boolean isAnonymous) {
        super();
        this.title = title;
        this.course = course;
        this.tags = tags;
        this.suggestion = suggestion;
        this.isAnonymous = isAnonymous;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
}
