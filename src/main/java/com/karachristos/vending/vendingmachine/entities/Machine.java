package com.karachristos.vending.vendingmachine.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Machine {
    private List<Position> positions = new ArrayList<>();
    private int rows;
    private int columns;
    private double yesterdays;
    private double todays;

    private boolean first = true;

    private LocalDateTime lastUpdated ;

    public Machine(List<Position> positions) {
        this.positions = positions;
    }

    public Machine() {
    }

    public Machine(List<Position> positions, int rows, int columns) {
        this.positions = positions;
        this.rows = rows;
        this.columns = columns;
    }

    public double getYesterdays() {
        return yesterdays;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public double getTodays() {
        return todays;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setYesterdays(double yesterdays) {
        this.yesterdays = yesterdays;
    }

    public void setTodays(double todays) {
        this.todays = todays;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int colums) {
        this.columns = colums;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}