package com.karachristos.vending.vendingmachine.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.karachristos.vending.vendingmachine.entities.Merchant;
import com.karachristos.vending.vendingmachine.entities.Position;

import java.io.IOException;
import java.util.ArrayList;

public class PositionAdapter extends TypeAdapter<Position> {
    private MerchantAdapter merchantAdapter = new MerchantAdapter();
    private DimensionsAdapter dimensionsAdapter = new DimensionsAdapter();
    private CordinatesAdapter coordinatesAdapter = new CordinatesAdapter();

    @Override
    public void write(JsonWriter out, Position position) throws IOException {
        out.beginObject();
        out.name("position_name").value(position.getPosition_name());
        out.name("merchants");
        writeMerchants(out, position.getMerchants());
        out.name("dimensions_of_position");
        dimensionsAdapter.writeDimensions(out, position.getDimensions_of_position());
        out.name("coordinates");
        coordinatesAdapter.write(out, position.getCordinates());
        out.endObject();
    }

    private void writeMerchants(JsonWriter out, ArrayList<Merchant> merchants) throws IOException {
        out.beginArray();
        for (Merchant merchant : merchants) {
            merchantAdapter.write(out, merchant);
        }
        out.endArray();
    }

    @Override
    public Position read(JsonReader in) throws IOException {
        Position position = new Position();
        in.beginObject();
        while (in.hasNext()) {
            String fieldName = in.nextName();
            if ("position_name".equals(fieldName)) {
                position.setPosition_name(in.nextString());
            } else if ("merchants".equals(fieldName)) {
                position.setMerchants(readMerchants(in));
            } else if ("dimensions_of_position".equals(fieldName)) {
                position.setDimensions_of_position(dimensionsAdapter.readDimensions(in));
            } else if ("coordinates".equals(fieldName)) {
                position.setCordinates(coordinatesAdapter.read(in));
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return position;
    }

    private ArrayList<Merchant> readMerchants(JsonReader in) throws IOException {
        ArrayList<Merchant> merchants = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            Merchant merchant = merchantAdapter.read(in);
            merchants.add(merchant);
        }
        in.endArray();
        return merchants;
    }
}
