package com.r2s.notemanagementsystem.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "category_table")
public class Category {

    @PrimaryKey(autoGenerate = true)
    private int cateId;

    private String nameCate;
    private String createdDate;

    /**
     * Constructor
     * @param nameCate
     */
    public Category(int cateId,String nameCate) {
        this.cateId = cateId;
        this.nameCate = nameCate;
    }


    /**
     * get id Category
     * @return
     */
    public int getCateId() {
        return cateId;
    }

    /**
     * set id Category
     * @param cateId
     */
    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    /**
     * get name Category
     * @return
     */
    public String getNameCate() {
        return nameCate;
    }

    /**
     * set name Category
     * @param nameCate
     */
    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    /**
     * get date created
     * @return
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * set date created
     * @param createdDate
     */
    public void setCreatedDate(String createdDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(Calendar.getInstance().getTime());
        createdDate = dateTime;
        this.createdDate = createdDate;
    }
}
