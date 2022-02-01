package com.laba2.dao;

import com.laba2.models.User;

import java.util.List;

public interface DaoUser {
    List<User> selectAllUser();

    void deleteUser(int id);

    void createUser(User user);
}
