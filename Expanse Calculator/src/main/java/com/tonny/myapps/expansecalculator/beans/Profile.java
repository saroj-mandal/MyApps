package com.tonny.myapps.expansecalculator.beans;

import java.util.Date;

/**
 * Created by Tonny on 9/1/2014.
 */
public class Profile {
    private long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Date createDate;
    private Date updateDate;
    private boolean checked;

    public Profile() {

    }

    public Profile(String firstName) {
        this.firstName = firstName;
    }

    public Profile(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public void toggle() {
        setChecked(!isChecked());
    }
}
