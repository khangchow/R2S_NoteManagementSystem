package com.r2s.demo.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "priority_table")
public class Priority {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String createdDate;
    private int authorId;

    public Priority(String name, String createdDate, int authorId) {
        this.name = name;
        this.createdDate = createdDate;
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
