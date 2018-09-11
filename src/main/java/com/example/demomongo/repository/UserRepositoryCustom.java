package com.example.demomongo.repository;

public interface UserRepositoryCustom {
    void pullFavorite(String id, Long favoriteID, String type);
}
