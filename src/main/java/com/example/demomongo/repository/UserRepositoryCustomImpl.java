package com.example.demomongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void pullFavorite(String id, Long favoriteID, String type) {
        mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(id)),new Update().pull("favorites",
                Query.query(Criteria.where("favoriteID").is(favoriteID)
                        .and("type").is(type))),"user");
    }
}
