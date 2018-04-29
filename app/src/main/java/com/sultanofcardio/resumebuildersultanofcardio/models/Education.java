package com.sultanofcardio.resumebuildersultanofcardio.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sultanofcardio
 */

public class Education {

    private String institution;

    private String major;

    private String gpa;

    private List<String> activities;

    public Education(String institution, String major, String gpa, List<String> activities) {
        this.institution = institution;
        this.major = major;
        this.gpa = gpa;
        this.activities = activities;
    }

    public Education() {
        this.institution = "";
        this.major = "";
        this.gpa = "";
        this.activities = new ArrayList<>();
    }

    public String getInstitution() {
        return institution;
    }

    public Education setInstitution(String institution) {
        this.institution = institution;
        return this;
    }

    public String getMajor() {
        return major;
    }

    public Education setMajor(String major) {
        this.major = major;
        return this;
    }

    public String getGpa() {
        return gpa;
    }

    public Education setGpa(String gpa) {
        this.gpa = gpa;
        return this;
    }

    public List<String> getActivities() {
        return activities;
    }

    public Education setActivities(List<String> activities) {
        this.activities = activities;
        return this;
    }

    public Education addActivity(String activity){
        if(activities == null)
            activities = new ArrayList<>();
        activities.add(activity);
        return this;
    }
}
