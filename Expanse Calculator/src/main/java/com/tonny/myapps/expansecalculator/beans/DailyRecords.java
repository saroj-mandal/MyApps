package com.tonny.myapps.expansecalculator.beans;

import java.util.Date;
import java.util.List;

/**
 * Created by Tonny on 9/1/2014.
 */
public class DailyRecords {
    private long id;
    private String description;
    private double amount;
    private long payerId;
    private List<Long> participantIdList;
    private long expanseId;
    private Date createDate;
    private Date updateDate;

    public DailyRecords() {

    }

    public DailyRecords(double amount, long payerId, long expanseId) {
        this.amount = amount;
        this.payerId = payerId;
        this.expanseId = expanseId;
    }

    public DailyRecords(long id, String description, double amount, long payerId, long expanseId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.payerId = payerId;
        this.expanseId = expanseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPayerId() {
        return payerId;
    }

    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }

    public List<Long> getParticipantIdList() {
        return participantIdList;
    }

    public void setParticipantIdList(List<Long> participantIdList) {
        this.participantIdList = participantIdList;
    }

    public long getExpanseId() {
        return expanseId;
    }

    public void setExpanseId(long expanseId) {
        this.expanseId = expanseId;
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
