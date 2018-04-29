package com.sultanofcardio.resumebuildersultanofcardio.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sultanofcardio
 */

public class Experience {

    private String company;

    private String role;

    private String startMonth;

    private String startYear;

    private String endMonth;

    private String endYear;

    private String description;

    private List<String> notableDuties;

    public Experience(String company, String role, String startMonth, String startYear,
                      String endMonth, String endYear, String description,
                      List<String> notableDuties) {
        this.company = company;
        this.role = role;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.description = description;
        this.notableDuties = notableDuties;
    }

    public Experience() {
        this.company = "";
        this.role = "";
        this.startMonth = "";
        this.startYear = "";
        this.endMonth = "";
        this.endYear = "";
        this.description = "";
        this.notableDuties = new ArrayList<>();
    }

    public String getCompany() {
        return company;
    }

    public Experience setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Experience setRole(String role) {
        this.role = role;
        return this;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public Experience setStartMonth(String startMonth) {
        this.startMonth = startMonth;
        return this;
    }

    public String getStartYear() {
        return startYear;
    }

    public Experience setStartYear(String startYear) {
        this.startYear = startYear;
        return this;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public Experience setEndMonth(String endMonth) {
        this.endMonth = endMonth;
        return this;
    }

    public String getEndYear() {
        return endYear;
    }

    public Experience setEndYear(String endYear) {
        this.endYear = endYear;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Experience setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getNotableDuties() {
        return notableDuties;
    }

    public Experience setNotableDuties(List<String> notableDuties) {
        this.notableDuties = notableDuties;
        return this;
    }

    public Experience addDuty(String duty){
        if(notableDuties == null)
            notableDuties = new ArrayList<>();
        notableDuties.add(duty);
        return this;
    }
}
