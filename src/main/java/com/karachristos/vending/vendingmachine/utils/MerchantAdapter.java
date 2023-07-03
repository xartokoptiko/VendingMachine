package com.karachristos.vending.vendingmachine.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.karachristos.vending.vendingmachine.entities.Merchant;

import java.io.IOException;

public class MerchantAdapter extends TypeAdapter<Merchant> {
    private DimensionsAdapter dimensionsAdapter = new DimensionsAdapter();


    @Override
    public void write(JsonWriter out, Merchant merchant) throws IOException {
        out.beginObject();
        out.name("name").value(merchant.getName());
        out.name("brand").value(merchant.getBrand());
        out.name("summary").value(merchant.getSummary());
        out.name("volume").value(merchant.getVolume());
        out.name("cost").value(merchant.getCost());
        out.name("dimensions");
        dimensionsAdapter.writeDimensions(out, merchant.getDimensions());
        out.name("url").value(merchant.getUrl());
        out.endObject();
    }

    @Override
    public Merchant read(JsonReader in) throws IOException {
        Merchant merchant = new Merchant();
        in.beginObject();
        while (in.hasNext()) {
            String fieldName = in.nextName();
            if ("name".equals(fieldName)) {
                merchant.setName(in.nextString());
            } else if ("brand".equals(fieldName)) {
                merchant.setBrand(in.nextString());
            } else if ("summary".equals(fieldName)) {
                merchant.setSummary(in.nextString());
            } else if ("dimensions".equals(fieldName)) {
                merchant.setDimensions(dimensionsAdapter.readDimensions(in));
            } else if ("volume".equals(fieldName)) {
                merchant.setVolume(in.nextString());
            } else if ("url".equals(fieldName)) {
                merchant.setUrl(in.nextString());
            }else if ("cost".equals(fieldName)) {
                merchant.setCost(in.nextDouble());
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return merchant;
    }
}
