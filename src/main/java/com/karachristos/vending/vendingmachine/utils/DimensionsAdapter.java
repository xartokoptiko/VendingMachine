package com.karachristos.vending.vendingmachine.utils;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.karachristos.vending.vendingmachine.entities.subentities.Dimensions;

import java.io.IOException;

public class DimensionsAdapter {
    public void writeDimensions(JsonWriter out, Dimensions dimensions) throws IOException {
        out.beginObject();
        out.name("x").value(dimensions.getX());
        out.name("y").value(dimensions.getY());
        out.name("z").value(dimensions.getZ());
        out.endObject();
    }

    public Dimensions readDimensions(JsonReader in) throws IOException {
        Dimensions dimensions = new Dimensions();
        in.beginObject();
        while (in.hasNext()) {
            String fieldName = in.nextName();
            if ("x".equals(fieldName)) {
                dimensions.setX(in.nextDouble());
            } else if ("y".equals(fieldName)) {
                dimensions.setY(in.nextDouble());
            } else if ("z".equals(fieldName)) {
                dimensions.setZ(in.nextDouble());
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return dimensions;
    }
}


