package com.laba2.dao;


import com.laba2.models.Location;

import java.util.List;

public interface DaoLocation {

    List<Location> selectAllLocation();

    void deleteLocation(int id);

    void createLocation(Location location);
}
