package com.sultanofcardio.resumebuildersultanofcardio.util;

import com.activeandroid.serializer.TypeSerializer;
import com.sultanofcardio.resumebuildersultanofcardio.models.Education;
import com.sultanofcardio.resumebuildersultanofcardio.models.EducationList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author sultanofcardio
 */

public class EducationSerializer extends TypeSerializer {
    @Override
    public Class<?> getDeserializedType() {
        return EducationList.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public Object serialize(Object data) {
        String result;
        JSONArray array = new JSONArray();
        for(Education education: (EducationList) data) {
            JSONObject object = new JSONObject();
            try {
                object.put("institution", education.getInstitution());
                object.put("major", education.getMajor());
                object.put("gpa", education.getGpa());
                JSONArray activities = new JSONArray(education.getActivities());
                object.put("activities", activities);
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        result = array.toString();
        return result;
    }

    @Override
    public Object deserialize(Object data) {
        String string = (String) data;
        EducationList list = new EducationList();
        try{
            JSONArray array = new JSONArray(string);
            for(int j=0; j<array.length(); j++) {
                Education education = new Education();
                JSONObject object = array.getJSONObject(j);
                education.setInstitution(object.getString("institution"));
                education.setMajor(object.getString("major"));
                education.setGpa(object.getString("gpa"));
                JSONArray activities = object.getJSONArray("activities");
                for (int i = 0; i < activities.length(); i++)
                    education.addActivity(activities.getString(i));
                list.add(education);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
