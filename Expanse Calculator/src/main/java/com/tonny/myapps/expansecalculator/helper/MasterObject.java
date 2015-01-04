package com.tonny.myapps.expansecalculator.helper;

import com.tonny.myapps.expansecalculator.beans.DailyRecords;
import com.tonny.myapps.expansecalculator.beans.Expense;
import com.tonny.myapps.expansecalculator.beans.Profile;

import java.util.List;
import java.util.Map;

/**
 * Created by Tonny on 22-11-2014.
 */
public class MasterObject {
    static MasterObject masterObject = new MasterObject();
    private List<Expense> expenseList;
    private List<Profile> memberList;
    private Map<Long, List<DailyRecords>> expenseRecords;
    private Map<Long, List<Profile>> expenseParticipant;

    private MasterObject() {

    }

    public static MasterObject getInstance() {
        if (null == masterObject) {
            masterObject = new MasterObject();
        }

        return masterObject;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<Profile> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Profile> memberList) {
        this.memberList = memberList;
    }

    public Map<Long, List<DailyRecords>> getExpenseRecords() {
        return expenseRecords;
    }

    public void setExpenseRecords(Map<Long, List<DailyRecords>> expenseRecords) {
        this.expenseRecords = expenseRecords;
    }

    public Map<Long, List<Profile>> getExpenseParticipant() {
        return expenseParticipant;
    }

    public void setExpenseParticipant(Map<Long, List<Profile>> expenseParticipant) {
        this.expenseParticipant = expenseParticipant;
    }
}
