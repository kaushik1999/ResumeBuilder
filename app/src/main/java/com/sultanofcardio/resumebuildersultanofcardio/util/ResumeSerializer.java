package com.sultanofcardio.resumebuildersultanofcardio.util;

import com.sultanofcardio.resumebuildersultanofcardio.models.Education;
import com.sultanofcardio.resumebuildersultanofcardio.models.EducationList;
import com.sultanofcardio.resumebuildersultanofcardio.models.Experience;
import com.sultanofcardio.resumebuildersultanofcardio.models.ExperienceList;
import com.sultanofcardio.resumebuildersultanofcardio.models.Reference;
import com.sultanofcardio.resumebuildersultanofcardio.models.ReferenceList;
import com.sultanofcardio.resumebuildersultanofcardio.models.Resume;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author sultanofcardio
 */

public class ResumeSerializer {

    public static String serialize(Resume resume){
        JSONObject object = new JSONObject();
        try{
            object.put("name", resume.getName());
            object.put("email", resume.getEmail());
            object.put("number", resume.getNumber());
            object.put("website", resume.getWebsite());
            object.put("address", resume.getAddress());
            object.put("summary", resume.getSummary());
            JSONArray experiences = new JSONArray();
            for(Experience experience: resume.getExperiences()){
                JSONObject experienceObject = new JSONObject();
                experienceObject.put("company", experience.getCompany());
                experienceObject.put("role", experience.getRole());
                experienceObject.put("start_month", experience.getStartMonth());
                experienceObject.put("start_year", experience.getStartYear());
                experienceObject.put("end_month", experience.getEndMonth());
                experienceObject.put("end_year", experience.getEndYear());
                experienceObject.put("description", experience.getDescription());
                JSONArray duties = new JSONArray();
                for(String duty: experience.getNotableDuties()){
                    duties.put(duty);
                }
                experienceObject.put("notable_duties", duties);
                experiences.put(experienceObject);
            }
            object.put("experience", experiences);

            JSONArray educationArray = new JSONArray();
            for(Education education: resume.getEducation()){
                JSONObject educationObject = new JSONObject();
                educationObject.put("institution", education.getInstitution());
                educationObject.put("major", education.getMajor());
                educationObject.put("gpa", education.getGpa());
                JSONArray activities = new JSONArray();
                for(String activity: education.getActivities())
                    activities.put(activity);
                educationObject.put("activities", activities);
                educationArray.put(educationObject);
            }
            object.put("education", educationArray);

            JSONArray references = new JSONArray();
            for(Reference reference: resume.getReferenceList()){
                JSONObject referenceObject = new JSONObject();
                referenceObject.put("name", reference.getName());
                referenceObject.put("title", reference.getTitle());
                referenceObject.put("number", reference.getNumber());
                referenceObject.put("email", reference.getEmail());
                references.put(referenceObject);
            }
            object.put("references", references);

            JSONArray skills = new JSONArray();
            for(String skill: resume.getSkills()){
                skills.put(skill);
            }
            object.put("skills", skills);
        } catch (JSONException e){
            e.printStackTrace();
        }

        return object.toString();
    }

    public static Resume deserialize(String data){
        Resume resume = new Resume();
        try{
            JSONObject object = new JSONObject(data);
            resume.setName(object.getString("name"));
            resume.setEmail(object.getString("email"));
            resume.setNumber(object.getString("number"));
            resume.setWebsite(object.getString("website"));
            resume.setAddress(object.getString("address"));
            resume.setSummary(object.getString("summary"));

            ExperienceList experienceList = new ExperienceList();
            JSONArray experiences = object.getJSONArray("experience");
            for(int i=0; i<experiences.length(); i++){
                JSONObject experienceObject = experiences.getJSONObject(i);
                Experience experience = new Experience();
                experience.setCompany(experienceObject.getString("company"));
                experience.setRole(experienceObject.getString("role"));
                experience.setStartMonth(experienceObject.getString("start_month"));
                experience.setStartYear(experienceObject.getString("start_year"));
                experience.setEndMonth(experienceObject.getString("end_month"));
                experience.setEndYear(experienceObject.getString("end_year"));
                experience.setDescription(experienceObject.getString("description"));
                JSONArray duties = experienceObject.getJSONArray("notable_duties");
                for(int j=0; j<duties.length(); j++){
                    experience.addDuty(duties.getString(j));
                }
                experienceList.add(experience);
            }
            resume.setExperiences(experienceList);

            EducationList educationList = new EducationList();
            JSONArray educationArray = object.getJSONArray("education");
            for(int i=0; i<educationArray.length(); i++){
                Education education = new Education();
                JSONObject educationObject = educationArray.getJSONObject(i);
                education.setInstitution(educationObject.getString("institution"));
                education.setMajor(educationObject.getString("major"));
                education.setGpa(educationObject.getString("gpa"));
                JSONArray activities = educationObject.getJSONArray("activities");
                for(int j = 0; j< activities.length(); j++)
                    education.addActivity(activities.getString(j));
                educationList.add(education);
            }
            resume.setEducation(educationList);

            ReferenceList referenceList = new ReferenceList();
            JSONArray references = object.getJSONArray("references");
            for(int i = 0; i<references.length(); i++){
                Reference reference = new Reference();
                JSONObject referenceObject = references.getJSONObject(i);
                reference.setName(referenceObject.getString("name"));
                reference.setTitle(referenceObject.getString("title"));
                reference.setNumber(referenceObject.getString("number"));
                reference.setEmail(referenceObject.getString("email"));
                references.put(referenceObject);
                referenceList.add(reference);
            }
            resume.setReferenceList(referenceList);

            JSONArray skills = object.getJSONArray("skills");
            for(int i=0; i<skills.length(); i++){
                resume.addSkill(skills.getString(i));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return resume;
    }
}
