package com.laba2.models;

import java.util.Objects;

public class Requirements {

    private int reqId;
    private String material;
    private String color;
    private String type;
    private int floorsCount;

    public Requirements() {
        super();
    }

    public Requirements(int reqId, String material, String color, String type, int floorsCount) {
        this.reqId = reqId;
        this.material = material;
        this.color = color;
        this.type = type;
        this.floorsCount = floorsCount;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(int floorsCount) {
        this.floorsCount = floorsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requirements that = (Requirements) o;
        return reqId == that.reqId && floorsCount == that.floorsCount && Objects.equals(material, that.material) && Objects.equals(color, that.color) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reqId, material, color, type, floorsCount);
    }

    @Override
    public String toString() {
        return "Requirements{" +
                "reqId=" + reqId +
                ", material='" + material + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", floorsCount=" + floorsCount +
                '}';
    }
}

