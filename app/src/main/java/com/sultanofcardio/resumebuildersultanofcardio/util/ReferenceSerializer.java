package com.sultanofcardio.resumebuildersultanofcardio.util;

import com.activeandroid.serializer.TypeSerializer;
import com.sultanofcardio.resumebuildersultanofcardio.models.Reference;
import com.sultanofcardio.resumebuildersultanofcardio.models.ReferenceList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author sultanofcardio
 */

public class ReferenceSerializer extends TypeSerializer {
    @Override
    public Class<?> getDeserializedType() {
        return ReferenceList.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public Object serialize(Object data) {
        JSONArray array = new JSONArray();
        for(Reference reference: (ReferenceList) data) {
            try {
                JSONObject object = new JSONObject();
                object.put("name", reference.getName());
                object.put("email", reference.getEmail());
                object.put("number", reference.getNumber());
                object.put("title", reference.getTitle());
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
        ReferenceList list = new ReferenceList();
        try{
            JSONArray array = new JSONArray(string);
            for(int i=0; i<array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Reference reference = new Reference();
                reference.setName(object.getString("name"));
                reference.setEmail(object.getString("email"));
                reference.setNumber(object.getString("number"));
                reference.setTitle(object.getString("title"));
                list.add(reference);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
