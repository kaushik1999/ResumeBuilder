package com.sultanofcardio.resumebuildersultanofcardio.util;

import com.activeandroid.serializer.TypeSerializer;
import com.sultanofcardio.resumebuildersultanofcardio.models.Experience;
import com.sultanofcardio.resumebuildersultanofcardio.models.ExperienceList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author sultanofcardio
 */

public class ExperienceSerializer extends TypeSerializer {
    @Override
    public Class<?> getDeserializedType() {
        return ExperienceList.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public Object serialize(Object data) {

        JSONArray array = new JSONArray();
        for(Experience experience: (ExperienceList) data) {
            try {
                JSONObject object = new JSONObject();
                object.put("company", experience.getCompany());
                object.put("role", experience.getRole());
                object.put("start_month", experience.getStartMonth());
                object.put("start_year", experience.getStartYear());
                object.put("end_month", experience.getEndMonth());
                object.put("end_year", experience.getEndYear());
                object.put("description", experience.getDescription());
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return array.toString();
    }

    @Override
    public Object deserialize(Object data) {
        ExperienceList list = new ExperienceList();
        String string = (String) data;
        try {
            JSONArray array = new JSONArray(string);
            for(int i=0; i<array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Experience experience = new Experience();
                experience.setCompany(object.getString("company"));
                experience.setRole(object.getString("role"));
                experience.setStartMonth(object.getString("start_month"));
                experience.setStartYear(object.getString("start_year"));
                experience.setEndMonth(object.getString("end_month"));
                experience.setEndYear(object.getString("end_year"));
                experience.setDescription(object.getString("description"));
                list.add(experience);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
