package com.sultanofcardio.resumebuildersultanofcardio.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * @author sultanofcardio
 */
@Table(name = "Resume")
public class Resume extends Model {

    @Column
    private int resumeType;

    @Column
    private String title;

    @Column
    private String stylesheet;

    @Column
    private String thumbnail;

    @Column
    private String name;

    @Column
    private String number;

    @Column
    private String email;

    @Column
    private String website;

    @Column
    private String address;

    @Column
    private String summary;

    @Column
    private SkillsList skills;

    @Column
    private ExperienceList experiences;

    @Column
    private EducationList education;

    @Column
    private ReferenceList referenceList;

    public Resume(int resumeType, String title, String stylesheet, String thumbnail, String name, String number,
                  String email, String website, String address, String summary, SkillsList skills,
                  ExperienceList experiences, EducationList education, ReferenceList referenceList) {
        this.resumeType = resumeType;
        this.title = title;
        this.stylesheet = stylesheet;
        this.thumbnail = thumbnail;
        this.name = name;
        this.number = number;
        this.email = email;
        this.website = website;
        this.address = address;
        this.summary = summary;
        this.skills = skills;
        this.experiences = experiences;
        this.education = education;
        this.referenceList = referenceList;
    }

    public Resume() {
        this.resumeType = 1;
        this.title = "";
        this.stylesheet = "";
        this.thumbnail = "";
        this.name = "";
        this.number = "";
        this.email = "";
        this.website = "";
        this.address = "";
        this.summary = "";
        this.skills = new SkillsList();
        this.experiences = new ExperienceList();
        this.education = new EducationList();
        this.referenceList = new ReferenceList();
    }

    public int getResumeType() {
        return resumeType;
    }

    public Resume setResumeType(int resumeType) {
        this.resumeType = resumeType;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Resume setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStylesheet() {
        return stylesheet;
    }

    public Resume setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Resume setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resume setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Resume setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Resume setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public Resume setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Resume setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Resume setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public SkillsList getSkills() {
        return skills;
    }

    public Resume setSkills(SkillsList skills) {
        this.skills = skills;
        return this;
    }

    public Resume addSkill(String skill){
        if(skills == null)
            skills = new SkillsList();
        skills.add(skill);
        return this;
    }

    public ExperienceList getExperiences() {
        return experiences;
    }

    public Resume setExperiences(ExperienceList experiences) {
        this.experiences = experiences;
        return this;
    }

    public Resume addExperience(Experience experience){
        experiences.add(experience);
        return this;
    }

    public EducationList getEducation() {
        return education;
    }

    public Resume setEducation(EducationList education) {
        this.education = education;
        return this;
    }

    public Resume addEducation(Education institution){
        education.add(institution);
        return this;
    }

    public ReferenceList getReferenceList() {
        return referenceList;
    }

    public Resume setReferenceList(ReferenceList referenceList) {
        this.referenceList = referenceList;
        return this;
    }

    public Resume addReference(Reference reference){
        referenceList.add(reference);
        return this;
    }
}
