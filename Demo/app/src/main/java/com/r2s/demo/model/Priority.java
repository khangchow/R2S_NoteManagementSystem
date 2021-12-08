package com.r2s.demo.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "priority_table")
public class Priority {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String createdDate;

    public Priority(int id, String name, String createdDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
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
}
