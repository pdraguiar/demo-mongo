package com.example.demomongo.repository;

import com.example.demomongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{'id': ?0, '$pull': {'favorites': {'favoriteID': ?1, 'type': ?2} } }")
    void deleteByUserAndFavorite(String id, Long favoriteID, String type);
}
