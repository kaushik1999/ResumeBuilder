package com.sultanofcardio.resumebuildersultanofcardio.util;

import com.activeandroid.serializer.TypeSerializer;
import com.sultanofcardio.resumebuildersultanofcardio.models.Reference;
import com.sultanofcardio.resumebuildersultanofcardio.models.ReferenceList;
import com.sultanofcardio.resumebuildersultanofcardio.models.SkillsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author sultanofcardio
 */

public class SkillSerializer extends TypeSerializer {
    @Override
    public Class<?> getDeserializedType() {
        return SkillsList.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public Object serialize(Object data) {
        JSONArray array = new JSONArray();
        for(String skill: (SkillsList) data) {
            try {
                JSONObject object = new JSONObject();
                object.put("skill", skill);
                array.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return array.toString();
    }

    @Override
    public Object deserialize(Object data) {
        String string = (String) data;
        SkillsList list = new SkillsList();
        try{
            JSONArray array = new JSONArray(string);
            for(int i=0; i<array.length(); i++) {
                list.add(array.getJSONObject(i).getString("skill"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
