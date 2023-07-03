package com.karachristos.vending.vendingmachine.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.karachristos.vending.vendingmachine.entities.subentities.Cordinates;

import java.io.IOException;

public class CordinatesAdapter extends TypeAdapter<Cordinates> {

    @Override
    public void write(JsonWriter out, Cordinates coordinates) throws IOException {
        out.beginObject();
        out.name("x").value(coordinates.getX());
        out.name("y").value(coordinates.getY());
        out.endObject();
    }

    @Override
    public Cordinates read(JsonReader in) throws IOException {
        Cordinates coordinates = new Cordinates();
        in.beginObject();
        while (in.hasNext()) {
            String fieldName = in.nextName();
            if ("x".equals(fieldName)) {
                coordinates.setX(in.nextDouble());
            } else if ("y".equals(fieldName)) {
                coordinates.setY(in.nextDouble());
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return coordinates;

    }

}