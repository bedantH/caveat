package com.example.mad_project;

import static android.R.attr.name;

import androidx.annotation.NonNull;

public class Query {
    int id;
    String title;
    String course;
    String tags;
    String query;
    boolean isAnonymous;

    public Query(){
        super();
    }

    public Query(String title, String course, String tags, String query, boolean isAnonymous) {
        super();
        this.title = title;
        this.course = course;
        this.tags = tags;
        this.query = query;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
}
