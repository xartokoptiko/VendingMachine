package com.karachristos.vending.vendingmachine.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.karachristos.vending.vendingmachine.entities.Machine;
import com.karachristos.vending.vendingmachine.entities.Position;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MachineAdapter extends TypeAdapter<Machine> {
    private PositionAdapter positionAdapter = new PositionAdapter();

    @Override
    public void write(JsonWriter out, Machine machine) throws IOException {
        out.beginObject();
        out.name("positions");
        writePositions(out, machine.getPositions());
        out.name("rows").value(machine.getRows());
        out.name("columns").value(machine.getColumns());
        out.name("yesterdays").value(machine.getYesterdays());
        out.name("todays").value(machine.getTodays());
        out.name("first").value(machine.isFirst());
        out.name("lastUpdated").value(machine.getLastUpdated().toString());
        out.endObject();
    }

    @Override
    public Machine read(JsonReader in) throws IOException {
        Machine machine = new Machine(new ArrayList<>());
        int rows = 0;
        int columns = 0;
        double yesterdays = 0.0;
        double todays = 0.0;
        boolean first = false;
        String lastUpdatedStr = null;
        in.beginObject();
        while (in.hasNext()) {
            String fieldName = in.nextName();
            if ("positions".equals(fieldName)) {
                machine.setPositions(readPositions(in));
            } else if ("rows".equals(fieldName)) {
                rows = in.nextInt();
            } else if ("columns".equals(fieldName)) {
                columns = in.nextInt();
            } else if ("yesterdays".equals(fieldName)) {
                yesterdays = in.nextDouble();
            } else if ("todays".equals(fieldName)) {
                todays = in.nextDouble();
            } else if ("first".equals(fieldName)) {
                first = in.nextBoolean();
            } else if ("lastUpdated".equals(fieldName)) {
                lastUpdatedStr = in.nextString();
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        machine.setRows(rows);
        machine.setColumns(columns);
        machine.setYesterdays(yesterdays);
        machine.setTodays(todays);
        machine.setFirst(first);
        if(lastUpdatedStr == null){
            lastUpdatedStr = LocalDateTime.now().toString();
        }
        machine.setLastUpdated(LocalDateTime.parse(lastUpdatedStr));
        return machine;
    }

    private void writePositions(JsonWriter out, List<Position> positions) throws IOException {
        out.beginArray();
        for (Position position : positions) {
            positionAdapter.write(out, position);
        }
        out.endArray();
    }

    private List<Position> readPositions(JsonReader in) throws IOException {
        List<Position> positions = new ArrayList<>();
        in.beginArray();
        while (in.hasNext()) {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                continue;
            }
            positions.add(positionAdapter.read(in));
        }
        in.endArray();
        return positions;
    }
}

