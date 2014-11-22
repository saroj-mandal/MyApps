package com.tonny.myapps.expansecalculator.beans;


import java.util.Date;

/**
 * Created by smand6 on 9/1/2014.
 */
public class Expense {
    private long id;
    private String name;
    private String description;
    private Date createDate;
    private Date updateDate;

    public Expense() {

    }

    public Expense(String name) {
        this.name = name;
    }

    public Expense(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
