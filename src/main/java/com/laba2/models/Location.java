package com.laba2.models;

public class Location {

    private int locationId;
    private String city;
    private String address;
    private int area;
    private String permission;

    public Location() {
        super();
    }

    public Location(int locationId, String city, String address, int area, String permission) {
        this.locationId = locationId;
        this.city = city;
        this.address = address;
        this.area = area;
        this.permission = permission;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

}

